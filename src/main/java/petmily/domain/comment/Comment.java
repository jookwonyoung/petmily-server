package petmily.domain.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 자동 할당
    private Long commentId;

    @Column(nullable = false)
    private String postId;

    @Column(nullable = false)
    private String commentContent;

    @Column(nullable = false)
    private String userId;


    @Builder
    public Comment(String postId, String commentContent, String userId){
        this.postId = postId;
        this.commentContent = commentContent;
        this.userId = userId;
    }
}
