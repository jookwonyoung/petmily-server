package petmily.controller.dto;

import lombok.Getter;
import petmily.domain.place.Place;

@Getter
public class PlaceResponseDto {

    private Long placeId;
    private String email;
    private String placeName;
    private String phone;
    private String address;
    private String categories;
    private String url;

    public PlaceResponseDto(Place entity){
        this.placeId = entity.getPlaceId();
        this.email = entity.getEmail();
        this.placeName = entity.getPlaceName();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
        this.categories = entity.getCategories();
        this.url = entity.getUrl();
    }
}
