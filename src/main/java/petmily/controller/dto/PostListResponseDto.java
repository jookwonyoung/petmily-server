package petmily.controller.dto;

import lombok.Getter;
import petmily.domain.posts.Post;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Getter
public class PostListResponseDto {

    private Long postId;
    private String email;
    private byte[] postImg;
    private String postContent;

    public PostListResponseDto(Post entity)  {

        this.postId = entity.getPostId();
        this.email = entity.getEmail();
        try {
            String img = entity.getPostImg();
            InputStream in = new FileInputStream(img);
            this.postImg = in.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.postContent = entity.getPostContent();
    }

}
