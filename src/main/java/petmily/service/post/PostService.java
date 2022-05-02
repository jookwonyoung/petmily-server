package petmily.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.PostListResponseDto;
import petmily.controller.dto.PostResponseDto;
import petmily.controller.dto.PostSaveRequestDto;
import petmily.domain.posts.Post;
import petmily.domain.posts.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return  postRepository.save(requestDto.toEntity()).getPostId();
    }

    @Transactional
    public Long testSave(PostSaveRequestDto requestDto){
        return  postRepository.save(requestDto.toEntity()).getPostId();
    }

//    public PostResponseDto findById(long id){
//        Post entity = postRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
//
//        return new PostResponseDto(entity);
//    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
