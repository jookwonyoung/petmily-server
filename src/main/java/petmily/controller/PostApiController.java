package petmily.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petmily.controller.dto.PostListResponseDto;
import petmily.controller.dto.PostSaveRequestDto;
import petmily.controller.dto.UserSaveRequestDto;
import petmily.service.analysis.AnalysisService;
import petmily.service.post.PostService;
import petmily.service.user.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final UserService userService;
    private final PostService postService;
    private final AnalysisService analysisService;

    private String localPath = "/Users/jookwonyoung/Documents/petmily/testImg/post";
    private String ec2Path = "/home/ec2-user/petmilyServer/step1/imgDB/post";

    String postRootPath;    //post 폴더

    @PostMapping("/save")
    public String save(@RequestHeader(value = "email") String email, @RequestParam("userImg") String userImg, @RequestParam("postImg") MultipartFile files, @RequestParam("postContent") String content) {

        if (new File(ec2Path).exists()) {
            postRootPath = ec2Path;        //ec2-server
        } else {
            postRootPath = localPath;     //localhost
        }

        UserSaveRequestDto saveRequestDto = new UserSaveRequestDto();
        saveRequestDto.setEmail(email);
        saveRequestDto.setUserImg(userImg);
        Long userId = userService.save(saveRequestDto);

        String returnMessage = "";

        // 1. 파일 저장
        String conType = files.getContentType();
        if (!(conType.equals("image/png") || conType.equals("image/jpeg"))) {
            returnMessage = "올바르지 않은 파일";
        }

        String tmpPath = postRootPath + "/tmp/" + System.currentTimeMillis();
        File tmpFile = new File(tmpPath);
        try {
            files.transferTo(tmpFile);
        } catch (Exception e) {
            e.printStackTrace();
            returnMessage = "내부 서버 오류 - 파일 저장 실패";
        }

        // 2. 파일 검사
        String result = analysisService.detectAnimal(tmpPath);
        System.out.println(result);
        // Json to Object Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(result);
            if(node.get("detected").asText().equals("true")) {
                // 3. post 생성
                PostSaveRequestDto requestDto = new PostSaveRequestDto();
                requestDto.setEmail(email);
                requestDto.setPostContent(content);
                Long postId = postService.save(requestDto);    //저장할 postImg(filename)

                // 4. postImg 저장
                String filePath = postRootPath + "/" + postId;
                files.transferTo(new File(filePath));
                returnMessage = postId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMessage = "내부 서버 오류 - 파일 검사 실패";
        } finally {
            // 파일 삭제
            if(!tmpFile.delete()) {
                returnMessage = "내부 서버 오류 - 파일 삭제 실패";
            }
        }

        return returnMessage;
    }

    @GetMapping(value = "/getImg/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        InputStream in;
        try {
            try {
                in = new FileInputStream(ec2Path + "/" + id);   //파일 읽어오기
            } catch (Exception e) {
                in = new FileInputStream(localPath + "/" + id);   //파일 읽어오기
            }
            byte[] imgByteArray = in.readAllBytes();                    //byte로 변환
            in.close();
            return new ResponseEntity<byte[]>(imgByteArray, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findAll")
    public List<PostListResponseDto> findAll() {
        List<PostListResponseDto> responseDtoList = postService.findAllDesc();
        return responseDtoList;
    }
}
