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

    private String userImg;


    @Builder
    public User(String email, String userImg){
        this.email = email;
        this.userImg = userImg;
    }

    public void update(Long id) {
        this.userImg = id.toString();
    }

//    public User update(String userName, String userImg) {
//        this.userName = userName;
//        this.userImg = userImg;
//        this.type = type;
//        this.birth = birth;
//
//        return this;
//    }
//
//    public String getRoleKey() {
//        return this.role.getKey();
//    }

}
