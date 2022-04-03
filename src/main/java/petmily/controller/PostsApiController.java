package petmily.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.controller.dto.PostsResponseDto;
import petmily.controller.dto.PostsSaveRequestDto;
import petmily.controller.dto.PostsUpdateRequestDto;
import petmily.service.posts.PostsService;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/save")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/update")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/findById/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }
}
