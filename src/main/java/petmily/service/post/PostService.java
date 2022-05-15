package petmily.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.PostListResponseDto;
import petmily.controller.dto.PostSaveRequestDto;
import petmily.domain.like.LikeRepository;
import petmily.domain.posts.Post;
import petmily.domain.posts.PostRepository;
import petmily.service.like.LikeService;
import petmily.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;
//
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeService likeService;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        Long id =  postRepository.save(requestDto.toEntity()).getPostId();  //실제 db 저장
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));//수정할 객체(지금 저장한거)
        post.update(id);    //객체의 id를 이미지의 id로 보냄
        return id;
    }


    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        List<PostListResponseDto> result = postRepository.findAllDesc().stream()
                .map(post -> {
                    return new PostListResponseDto(post, userService.findImgByEmail(post.getEmail()));
                })
                .collect(Collectors.toList());
        return result;
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllMyLikePost(String email){
        if(likeService.checkPresent(email) == 1){
            List<PostListResponseDto> result = postRepository.findAllMyLikePost(email).stream()
                    .map(post -> {
                        return new PostListResponseDto(post, userService.findImgByEmail(post.getEmail()));
                    })
                    .collect(Collectors.toList());

            return result;
        }else{
            return null;
        }
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
