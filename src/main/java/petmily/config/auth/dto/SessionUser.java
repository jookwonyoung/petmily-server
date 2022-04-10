package petmily.config.auth.dto;

import lombok.Getter;
import petmily.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.picture = user.getUserImg();
    }
}