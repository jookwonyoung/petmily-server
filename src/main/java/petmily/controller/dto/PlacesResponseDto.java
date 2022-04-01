package petmily.controller.dto;

import lombok.Getter;
import petmily.domain.places.Places;

import javax.swing.text.html.parser.Entity;

@Getter
public class PlacesResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String address;
    private String url;
    private String categories;

    public PlacesResponseDto(Places entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
        this.url = entity.getUrl();
        this.categories = entity.getCategories();
    }
}
