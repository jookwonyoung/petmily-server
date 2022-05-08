package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petmily.controller.dto.PostListResponseDto;
import petmily.controller.dto.PostSaveRequestDto;
import petmily.service.post.PostService;

import java.io.File;
import java.util.List;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/save")                              //이미지 파일
    public Long save(@RequestHeader(value="email") String email, @RequestParam("postImg") MultipartFile files, @RequestParam("postContent") String content){
        PostSaveRequestDto requestDto = new PostSaveRequestDto();
        requestDto.setEmail(email);
        requestDto.setPostContent(content);
        Long postId =  postService.save(requestDto);    //저장할 postImg(filename)


        String rootPath = "/home/ec2-user/petmilyServer/step1/imgDB/post";        //ec2-server
        //String rootPath = "/Users/jookwonyoung/Documents/petmily/testImg/post";     //localhost

        String emailPath = rootPath + "/" + email;  //최종 폴더

        //png, jpeg만 저장
        String conType = files.getContentType();
        if(!(conType.equals("image/png") || conType.equals("image/jpeg"))){
            Long error = null;
            return error;
        }

        //폴더
        if (!new File(emailPath).exists()) {
            try{
                new File(emailPath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        String filePath = emailPath + "/" + postId;
        try {
            files.transferTo(new File(filePath));
        }catch(Exception e) {
            e.printStackTrace();
        }

        return postId;
    }

//    @PostMapping("/testSave")
//    public Long testSave(@RequestHeader(value="email") String email, @RequestBody PostSaveRequestDto requestDto){
//        System.out.println("$$$$$$$$$$$$$$$$$$$"+email);
//        requestDto.setEmail(email);
//        return postService.save(requestDto);
//    }

//    @GetMapping("/findById/{id}")
//    public PostResponseDto findById (@PathVariable Long id){
//        return postService.findById(id);
//    }

    @GetMapping("/findAll")
    public List<PostListResponseDto> findAll() {
        return postService.findAllDesc();
    }

}
