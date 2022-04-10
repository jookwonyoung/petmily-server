package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.config.auth.LoginUser;
import petmily.config.auth.dto.SessionUser;
import petmily.controller.dto.PostListResponseDto;
import petmily.controller.dto.PostResponseDto;
import petmily.controller.dto.PostSaveRequestDto;
import petmily.service.post.PostService;

import java.util.List;

@RequestMapping("/post/api")
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/save")
    //@PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto, @LoginUser SessionUser user){
        //requestDto.setEmail(user.getEmail());
        return postService.save(requestDto);
    }

    @GetMapping("/findById/{id}")
    public PostResponseDto findById (@PathVariable Long id){
        return postService.findById(id);
    }

    @GetMapping("/findAll")
    public List<PostListResponseDto> findAll() {
        return postService.findAllDesc();
    }

    //게시글 수정 기능은 안할 예정
//    @PutMapping("/update")
//    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
//        return postsService.update(id, requestDto);
//    }

}
