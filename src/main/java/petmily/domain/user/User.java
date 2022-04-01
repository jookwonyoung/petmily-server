package petmily.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String petName;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String birth;

    private String userPicture;

    private String petPicture;

    @Builder
    public User(String name, String petName, String type, String birth, String userPicture, String petPicture){
        this.name = name;
        this.petName = petName;
        this.type = type;
        this.birth = birth;
        this.userPicture = userPicture;
        this.petPicture = petPicture;
    }

}
