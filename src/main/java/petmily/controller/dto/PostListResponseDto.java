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

    public PostListResponseDto(Post entity) {

        String ec2Path = "/home/ec2-user/petmilyServer/step1/imgDB";

        this.postId = entity.getPostId();
        this.email = entity.getEmail();
        try {
            InputStream in;
            String img = entity.getPostImg();

            in = new FileInputStream(ec2Path + "/post/test");   //파일 읽어오기
            this.postImg = in.readAllBytes();
            in.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        this.postContent = entity.getPostContent();
    }

}
