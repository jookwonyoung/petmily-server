package petmily.service.places;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.PlacesListResponseDto;
import petmily.controller.dto.PlacesResponseDto;
import petmily.controller.dto.PlacesSaveRequestDto;
import petmily.controller.dto.PostsResponseDto;
import petmily.domain.places.Places;
import petmily.domain.places.PlacesRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlacesService {
    private final PlacesRepository placesRepository;

    @Transactional
    public Long save(PlacesSaveRequestDto requestDto){
        return placesRepository.save(requestDto.toEntity()).getId();
    }

    public PlacesResponseDto findById(long id){
        Places entity = placesRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 장소가 없습니다. id="+id));

        return new PlacesResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PlacesResponseDto> findAllDesc() {
        return placesRepository.findAllDesc().stream()
                .map(PlacesResponseDto::new)
                .collect(Collectors.toList());
    }
}
