package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
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

    private String localPath = "/Users/jookwonyoung/Documents/petmily/testImg";
    private String ec2Path = "/home/ec2-user/petmilyServer/step1/imgDB";
    String userRootPath;
    String postRootPath;

    @PostMapping("/save")
    public Long save(@RequestHeader(value="email") String email, @RequestParam("userImg") MultipartFile userImg, @RequestParam("postImg") MultipartFile files, @RequestParam("postContent") String content){

        if(new File(ec2Path+"/user").exists()){
            userRootPath = ec2Path+"/user";        //ec2-server
            postRootPath = ec2Path+"/post";        //ec2-server
        }else{
            userRootPath = localPath+"/user";     //localhost
            postRootPath = localPath+"/post";     //localhost
        }

        UserSaveRequestDto saveRequestDto = new UserSaveRequestDto();
        saveRequestDto.setEmail(email);
        saveRequestDto.setUserImg(email);
        Long userId = userService.save(saveRequestDto);

        PostSaveRequestDto requestDto = new PostSaveRequestDto();
        requestDto.setEmail(email);
        requestDto.setPostContent(content);
        Long postId =  postService.save(requestDto);    //저장할 postImg(filename)

        String userConType = userImg.getContentType();
        String conType = files.getContentType();
        if(!(userConType.equals("image/png") || userConType.equals("image/jpeg") || conType.equals("image/png") || conType.equals("image/jpeg"))){
            Long error = null;
            return error;
        }

        String userFilePath = userRootPath + "/" + userId;
        String filePath = postRootPath + "/" + postId;
        try {
            files.transferTo(new File(filePath));
            userImg.transferTo(new File(userFilePath));
        }catch(Exception e) {
            e.printStackTrace();
        }

        return postId;
    }

    @PostMapping("/testSave")
    public Long save(@RequestHeader(value="email") String email, @RequestParam("postImg") MultipartFile files, @RequestParam("postContent") String content){

        if(new File(ec2Path+"/user").exists()){
            userRootPath = ec2Path+"/user";        //ec2-server
            postRootPath = ec2Path+"/post";        //ec2-server
        }else{
            userRootPath = localPath+"/user";     //localhost
            postRootPath = localPath+"/post";     //localhost
        }

        PostSaveRequestDto requestDto = new PostSaveRequestDto();
        requestDto.setEmail(email);
        requestDto.setPostContent(content);
        Long postId =  postService.save(requestDto);    //저장할 postImg(filename)

        String conType = files.getContentType();
        if(!(conType.equals("image/png") || conType.equals("image/jpeg"))){
            Long error = null;
            return error;
        }

        String filePath = postRootPath + "/" + postId;
        try {
            files.transferTo(new File(filePath));
        }catch(Exception e) {
            e.printStackTrace();
        }

        return postId;
    }


    @GetMapping(value = "/findById/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public PostResponseDto findById (@PathVariable Long id) throws IOException {
        PostResponseDto tmpDto = postService.findById(id);

        InputStream in = new FileInputStream(localPath+"/post/"+id);   //파일 읽어오기
        byte[] imgByteArray = in.readAllBytes();                       //byte로 변환
        in.close();

        tmpDto.setPostImg(imgByteArray.toString());

        //PostEndListResponseDto resultDto = new PostEndListResponseDto(tmpDto.getPostId(), tmpDto.getEmail(), imgByteArray, tmpDto.getPostContent());
        //byte[] result = SerializationUtils.serialize(resultDto);

//        //테스트
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] fuck = outputStream.toByteArray();

        return tmpDto;
    }

    @GetMapping(value = "/getImg", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        InputStream in;
        try{
            in = new FileInputStream(ec2Path+"/post/test");   //파일 읽어오기
        }catch (Exception e){
            in = new FileInputStream(localPath+"/post/1");   //파일 읽어오기
        }
        byte[] imgByteArray = in.readAllBytes();                    //byte로 변환
        in.close();
        return new ResponseEntity<byte[]>(imgByteArray, HttpStatus.OK);
    }

    @GetMapping(value = "/findAll")
    public List<PostListResponseDto> findAll(){
        List<PostListResponseDto> responseDtoList = postService.findAllDesc();
        return responseDtoList;
    }
}
