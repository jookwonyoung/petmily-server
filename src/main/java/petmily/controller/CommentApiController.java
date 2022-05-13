package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.controller.dto.CommentListResponseDto;
import petmily.controller.dto.CommentSaveRequestDto;
import petmily.controller.dto.PlaceSaveRequestDto;
import petmily.service.comment.commentService;

import java.util.List;

@RequestMapping("/api/comment")
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final commentService commentService;

    @GetMapping(value = "/findByPostId/{postId}")
    public List<CommentListResponseDto> findByPostId(@PathVariable Long postId) {
        List<CommentListResponseDto> responseDtoList = commentService.findAllDesc(postId);
        return responseDtoList;
    }

    @PostMapping("/save")
    public Long save(@RequestHeader(value="email") String email, @RequestBody CommentSaveRequestDto requestDto){
        requestDto.setEmail(email);
        return commentService.save(requestDto);
    }

}
