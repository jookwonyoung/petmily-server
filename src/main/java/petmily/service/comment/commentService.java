package petmily.service.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.CommentListResponseDto;
import petmily.controller.dto.PostListResponseDto;
import petmily.domain.comment.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class commentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAllDesc(Long postId) {
        return commentRepository.findAllDesc(postId).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }
}
