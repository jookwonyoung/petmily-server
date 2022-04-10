package petmily.service.places;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.PlaceListResponseDto;
import petmily.controller.dto.PlaceResponseDto;
import petmily.controller.dto.PlaceSaveRequestDto;
import petmily.domain.place.Place;
import petmily.domain.place.PlaceRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public Long save(PlaceSaveRequestDto requestDto){
        return placeRepository.save(requestDto.toEntity()).getPlaceId();
    }

    public PlaceResponseDto findById(long id){
        Place entity = placeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 장소가 없습니다. id="+id));

        return new PlaceResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PlaceListResponseDto> findAllDesc() {
        return placeRepository.findAllDesc().stream()
                .map(PlaceListResponseDto::new)
                .collect(Collectors.toList());
    }
}
