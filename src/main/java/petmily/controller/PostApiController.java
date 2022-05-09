package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petmily.controller.dto.*;
import petmily.service.post.PostService;
import petmily.service.user.UserService;

import java.io.*;
import java.util.List;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final UserService userService;
    private final PostService postService;

    private String localPath = "/Users/jookwonyoung/Documents/petmily/testImg/post";
    private String ec2Path = "/home/ec2-user/petmilyServer/step1/imgDB/post";
    String rootPath;

    @PostMapping("/save")
    public Long save(@RequestHeader(value="email") String email, @RequestParam("userImg") MultipartFile userImg, @RequestParam("postImg") MultipartFile files, @RequestParam("postContent") String content){

        //user 객체
        UserSaveRequestDto saveRequestDto = new UserSaveRequestDto();
        saveRequestDto.setEmail(email);
        saveRequestDto.setUserImg(email);
        userService.save(saveRequestDto);
        //

        PostSaveRequestDto requestDto = new PostSaveRequestDto();
        requestDto.setEmail(email);
        requestDto.setPostContent(content);
        Long postId =  postService.save(requestDto);    //저장할 postImg(filename)

        if(new File(ec2Path).exists()){
            rootPath = ec2Path;        //ec2-server
        }else{
            rootPath = localPath;     //localhost
        }

        //png, jpeg만 저장
        String conType = files.getContentType();
        if(!(conType.equals("image/png") || conType.equals("image/jpeg"))){
            Long error = null;
            return error;
        }

        String filePath = rootPath + "/" + postId;
        try {
            files.transferTo(new File(filePath));
        }catch(Exception e) {
            e.printStackTrace();
        }

        return postId;
    }




    @GetMapping(value = "/findById/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<PostEndListResponseDto> findById (@PathVariable Long id) throws IOException {
        PostResponseDto tmpDto = postService.findById(id);

        InputStream in = new FileInputStream(localPath+"/"+id);   //파일 읽어오기
        byte[] imgByteArray = in.readAllBytes();                       //byte로 변환
        in.close();


        PostEndListResponseDto resultDto = new PostEndListResponseDto(tmpDto.getPostId(), tmpDto.getEmail(),
                                                                        imgByteArray, tmpDto.getPostContent());

        return new ResponseEntity<PostEndListResponseDto>(resultDto, HttpStatus.OK);
    }

    @GetMapping(value = "/findAll", produces = MediaType.IMAGE_JPEG_VALUE)
    public List<PostListResponseDto> findAll() throws IOException  {
        List<PostListResponseDto> responseDtoList = postService.findAllDesc();  //이미지 string 타입 객체 list

        return responseDtoList;
    }

    @GetMapping(value = "/getImg", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        InputStream in = new FileInputStream(ec2Path+"/1");   //파일 읽어오기
        byte[] imgByteArray = in.readAllBytes();                       //byte로 변환
        in.close();

        return new ResponseEntity<byte[]>(imgByteArray, HttpStatus.OK);
    }
}
