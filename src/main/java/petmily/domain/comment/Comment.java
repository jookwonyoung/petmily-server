package petmily.domain.comment;

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
}
