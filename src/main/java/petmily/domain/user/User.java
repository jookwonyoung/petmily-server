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

    private String phone;

    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String petName;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String petBirth;

    private String userImg;

    private String petImg;

    @Builder
    public User(String phone, String password, String name, String petName, String type, String petBirth, String userImg, String petImg){
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.petName = petName;
        this.type = type;
        this.petBirth = petBirth;
        this.userImg = userImg;
        this.petImg = petImg;
    }

}
