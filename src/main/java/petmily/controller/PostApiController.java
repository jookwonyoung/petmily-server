package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petmily.config.auth.LoginUser;
import petmily.config.auth.dto.SessionUser;
import petmily.controller.dto.PostListResponseDto;
import petmily.controller.dto.PostSaveRequestDto;
import petmily.service.post.PostService;

import java.io.File;
import java.util.List;
import java.util.Random;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/save")                              //이미지 파일
    public Long save(@RequestHeader(value="email") String email, @RequestParam("file") MultipartFile files, @RequestParam("postContent") String content){
        String rootPath = "/home/ec2-user/petmilyServer/step1/imgDB";
        //String rootPath = "/Users/jookwonyoung/testImg";
        String emailPath = rootPath + "/" + email;
        if (!new File(emailPath).exists()) {
            try{
                new File(emailPath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        Random random = new Random();
        String filename = String.valueOf(System.currentTimeMillis())+random.nextInt();
        String filePath = emailPath + "/" + filename;
        try {
            files.transferTo(new File(filePath));
        }catch(Exception e) {
            e.printStackTrace();
        }

        PostSaveRequestDto requestDto = new PostSaveRequestDto();

        requestDto.setPostImg(filename);
        requestDto.setEmail(email);
        requestDto.setPostContent(content);
        return postService.save(requestDto);
    }

    @PostMapping("/testSave")
    public Long testSave(@RequestHeader(value="email") String email, @RequestBody PostSaveRequestDto requestDto){
        System.out.println("$$$$$$$$$$$$$$$$$$$"+email);
        requestDto.setEmail(email);
        return postService.save(requestDto);
    }

//    @GetMapping("/findById/{id}")
//    public PostResponseDto findById (@PathVariable Long id){
//        return postService.findById(id);
//    }

    @GetMapping("/findAll")
    public List<PostListResponseDto> findAll() {
        return postService.findAllDesc();
    }

}
