package petmily.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 자동 할당
    private Long postId;

    @Column(nullable = false)
    private String email;

    private String postImg;

    @Column(columnDefinition = "TEXT")
    private String postContent;


    //생성자, PostsSaveRequestDto 의 toEntity() 에서 생성됨
    @Builder
    public Post(String email, String postImg, String postContent){
        this.email = email;
        this.postImg = postImg;
        this.postContent = postContent;
    }

    public void update(Long id) {
        this.postImg = id.toString();
    }


//    public void update(String title, String content){
//        //this.title = title;
//        this.content = content;
//    }
}
