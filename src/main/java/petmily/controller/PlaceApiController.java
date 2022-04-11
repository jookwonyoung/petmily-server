package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.controller.dto.PlaceListResponseDto;
import petmily.controller.dto.PlaceResponseDto;
import petmily.controller.dto.PlaceSaveRequestDto;
import petmily.service.places.PlaceService;

import java.util.List;

@RequestMapping("/api/place")
@RequiredArgsConstructor
@RestController
public class PlaceApiController {

    private final PlaceService placeService;

    @PostMapping("/save")
    public Long save(@RequestBody PlaceSaveRequestDto requestDto){
        return placeService.save(requestDto);
    }

    @GetMapping("/findById/{id}")
    public PlaceResponseDto findById (@PathVariable Long id){
        return placeService.findById(id);
    }

    @GetMapping("findAll")
    public List<PlaceListResponseDto> findAll () {
        return placeService.findAllDesc();
    }

}
