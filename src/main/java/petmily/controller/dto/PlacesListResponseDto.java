package petmily.controller.dto;

import lombok.Getter;
import petmily.domain.places.Places;

@Getter
public class PlacesListResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String address;
    private String url;
    private String categories;

    public PlacesListResponseDto(Places entity){
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
        this.url = entity.getUrl();
        this.categories = entity.getCategories();
    }
}
