package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.controller.dto.PlacesResponseDto;
import petmily.controller.dto.PlacesSaveRequestDto;
import petmily.service.places.PlacesService;

@RequiredArgsConstructor
@RestController
public class PlacesApiController {

    private final PlacesService placesService;

    @PostMapping("/api/v1/places")
    public Long save(@RequestBody PlacesSaveRequestDto requestDto){
        return placesService.save(requestDto);
    }

    @GetMapping("/api/v1/places/{id}")
    public PlacesResponseDto findById (@PathVariable Long id){
        return placesService.findById(id);
    }
}
