//조회용 dto
package petmily.controller.dto;

import lombok.Getter;
import petmily.domain.posts.Post;

@Getter
public class PostResponseDto {

    private Long postId;
    private String email;
    private String postImg;
    private String postContent;

    public PostResponseDto(Post entity){
        this.postId = entity.getPostId();
        this.email = entity.getEmail();
        this.postImg = entity.getPostImg();
        this.postContent = entity.getPostContent();
    }
}
