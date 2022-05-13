package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import petmily.controller.dto.CommentListResponseDto;
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
}
