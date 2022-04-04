package petmily.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import petmily.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String phone;
    private String password;
    private String name;
    private String petName;
    private String type;
    private String petBirth;
    private String userImg;
    private String petImg;

    @Builder
    public UserSaveRequestDto(String phone, String password, String name, String petName,
                              String type, String petBirth, String userImg, String petImg){
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.petName = petName;
        this.type = type;
        this.petBirth = petBirth;
        this.userImg = userImg;
        this.petImg = petImg;
    }

    public User toEntity() {
        return User.builder()
                .phone(phone)
                .password(password)
                .name(name)
                .petName(petName)
                .type(type)
                .petBirth(petBirth)
                .userImg(userImg)
                .petImg(petImg)
                .build();
    }
}
