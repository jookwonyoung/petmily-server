package petmily.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import petmily.domain.posts.Post;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Getter
@Setter
public class PostListResponseDto {

    private Long postId;
    private String email;
    private byte[] postImg;
    private String postContent;
    private String userImg;

    public PostListResponseDto(Post entity, String userImg) {
        //
        String ec2Path = "/home/ec2-user/petmilyServer/step1/imgDB";
        //String ec2Path = "/Users/jookwonyoung/Documents/petmily/testImg";

                this.postId = entity.getPostId();
        this.email = entity.getEmail();
        try {
            InputStream in;

            in = new FileInputStream(ec2Path + "/post/"+entity.getPostId());   //파일 읽어오기
            this.postImg = in.readAllBytes();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.postContent = entity.getPostContent();
        this.userImg = userImg;
    }

}
