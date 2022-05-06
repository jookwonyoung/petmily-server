package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.config.auth.LoginUser;
import petmily.config.auth.dto.SessionUser;
import petmily.controller.dto.PlaceListResponseDto;
import petmily.controller.dto.PlaceSaveRequestDto;
import petmily.service.place.PlaceService;

import java.util.List;

@RequestMapping("/api/place")
@RequiredArgsConstructor
@RestController
public class PlaceApiController {

    private final PlaceService placeService;

    @PostMapping("/save")
    public Long save(@RequestHeader(value="email") String email, @RequestBody PlaceSaveRequestDto requestDto){
        requestDto.setEmail(email);
        return placeService.save(requestDto);
    }

//    @GetMapping("/findById/{id}")
//    public PlaceResponseDto findById (@PathVariable Long id){
//        return placeService.findById(id);
//    }
//
//    @GetMapping("/findAll")
//    public List<PlaceListResponseDto> findAll () {
//        return placeService.findAllDesc();
//    }

    @GetMapping("/findByEmail")
    public List<PlaceListResponseDto> findByEmail(@RequestHeader(value="email") String email){
        return placeService.findByEmail(email);
    }

}
