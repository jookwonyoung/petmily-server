package petmily.controller.dto;

import lombok.Getter;
@Getter
public class PostEndListResponseDto{

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
