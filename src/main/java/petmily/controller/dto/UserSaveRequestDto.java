package petmily.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import petmily.domain.user.User;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String userName;
    private String email;
    private String userImg;
    private String type;
    private String birth;

    @Builder
    public UserSaveRequestDto(String userName, String email, String userImg, String type, String birth){
        this.userName = userName;
        this.email = email;
        this.userImg = userImg;
        this.type = type;
        this.birth = birth;

    }

    public User toEntity() {
        return User.builder()
                .userName(userName)
                .email(email)
                .userImg(userImg)
                .type(type)
                .birth(birth)
                .build();
    }
}
