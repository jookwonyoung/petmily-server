package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.controller.dto.PlacesResponseDto;
import petmily.controller.dto.PlacesSaveRequestDto;
import petmily.service.places.PlacesService;

@RequestMapping("/places")
@RequiredArgsConstructor
@RestController
public class PlacesApiController {

    private final PlacesService placesService;

    @PostMapping("/save")
    public Long save(@RequestBody PlacesSaveRequestDto requestDto){
        return placesService.save(requestDto);
    }

    @GetMapping("/findById/{id}")
    public PlacesResponseDto findById (@PathVariable Long id){
        return placesService.findById(id);
    }
}
