package petmily.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import petmily.domain.places.Places;

@Getter
@NoArgsConstructor
public class PlacesSaveRequestDto {
    private String name;
    private String phone;
    private String address;
    private String url;
    private String categories;

    @Builder
    public PlacesSaveRequestDto(String name, String phone, String address, String url, String categories){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.url = url;
        this.categories = categories;
    }

    public Places toEntity() {
        return Places.builder()
                .name(name)
                .phone(phone)
                .address(address).url(url)
                .categories(categories)
                .build();
    }

}
