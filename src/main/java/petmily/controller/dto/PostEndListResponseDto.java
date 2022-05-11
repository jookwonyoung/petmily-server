package petmily.controller.dto;

import lombok.Getter;
import org.springframework.http.HttpEntity;
import petmily.domain.posts.Post;

import java.io.Serializable;

@Getter
public class PostEndListResponseDto implements Serializable {

    private Long postId;
    private String email;
    private byte[] postImg;
    private String postContent;

    public PostEndListResponseDto(Long id, String email, byte[] img, String content){
        this.postId = id;
        this.email = email;
        this.postImg = img;
        this.postContent = content;
    }
}
