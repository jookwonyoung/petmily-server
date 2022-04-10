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
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userImg;

    private String type;

    private String birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String userName, String email, String userImg, String type, String birth, Role role){
        this.userName = userName;
        this.email = email;
        this.userImg = userImg;
        this.type = type;
        this.birth = birth;
        this.role = role;
    }


    public User update(String userName, String userImg) {
        this.userName = userName;
        this.userImg = userImg;
//        this.type = type;
//        this.birth = birth;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
