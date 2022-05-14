package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.controller.dto.LikeSaveRequestDto;
import petmily.controller.dto.UserListResponseDto;
import petmily.controller.dto.UserSaveRequestDto;
import petmily.service.like.LikeService;
import petmily.service.user.UserService;

import java.util.List;

@RequestMapping("/api/like")
@RequiredArgsConstructor
@RestController
public class LikeApiController {

    private final UserService userService;
    private final LikeService likeService;

    @PostMapping("/save")
    public Long createLike(@RequestHeader(value = "email") String email, @RequestParam("postId") Long postId, @RequestParam("userImg") String userImg){
        UserSaveRequestDto saveRequestDto = new UserSaveRequestDto();
        saveRequestDto.setEmail(email);
        saveRequestDto.setUserImg(userImg);
        userService.save(saveRequestDto);

        LikeSaveRequestDto requestDto = new LikeSaveRequestDto();
        requestDto.setEmail(email);
        requestDto.setPostId(postId);
        return likeService.createLike(requestDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestHeader(value = "email") String email, @RequestParam("postId") Long postId) {
        likeService.delete(email, postId);
    }

    @GetMapping("/aboutMyLike")
    public int findMyLike(@RequestHeader(value = "email") String email, @RequestParam("postId") Long postId){
        return likeService.findMyLike(email, postId);
    }

    @GetMapping("/count")
    public int countLike(@RequestParam("postId") Long postId){
        return likeService.countLike(postId);
    }

    @GetMapping("/users")
    public List<UserListResponseDto> findAllUser(@RequestParam("postId") Long postId){
        return likeService.findAllUser(postId);
    }

}
