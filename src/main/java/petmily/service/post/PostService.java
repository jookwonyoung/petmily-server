package petmily.service.post;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.client.FlaskTemplate;
import petmily.controller.dto.PostListResponseDto;
import petmily.controller.dto.PostSaveRequestDto;
import petmily.domain.posts.Post;
import petmily.domain.posts.PostRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final FlaskTemplate template = new FlaskTemplate(new RestTemplateBuilder());

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        Long id =  postRepository.save(requestDto.toEntity()).getPostId();  //실제 db 저장
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));//수정할 객체(지금 저장한거)
        post.update(id);    //객체의 id를 이미지의 id로 보냄
        return id;
    }


    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    public Boolean isThereCatAndDog(String filePath) {
        String result = template.requestDetectAnimal(filePath);

        // 통신이 제대로 안됬을 경우
        if (result == null) {
            return false;
        }

        // Json to Object Mapper
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = null;
        try {
            node = objectMapper.readTree(result);
            if (node.get("detected").asText().equals("true")) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
