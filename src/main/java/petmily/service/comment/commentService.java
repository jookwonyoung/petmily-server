package petmily.service.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.CommentListResponseDto;
import petmily.controller.dto.CommentSaveRequestDto;
import petmily.domain.comment.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class commentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long save(CommentSaveRequestDto requestDto){
        return commentRepository.save(requestDto.toEntity()).getCommentId();
    }

    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAllDesc(Long postId) {
        return commentRepository.findAllDesc(postId).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }
}
