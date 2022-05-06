package petmily.service.walk;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.*;
import petmily.domain.walk.Walk;
import petmily.domain.walk.WalkRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WalkService {

    private final WalkRepository walkRepository;

    @Transactional
    public Long save(WalkSaveRequestDto requestDto){
        return walkRepository.save(requestDto.toEntity()).getWalkId();
    }

    @Transactional(readOnly = true)
    public List<WalkListResponseDto> findAllDesc(int year, int month, int day, String email){
        return walkRepository.findAllDesc(year, month, day, email).stream()
                .map(WalkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(int num, int year, int month, int day, String email) {
        Walk walk = walkRepository.findByEmail(num, year, month, day, email);
        walkRepository.delete(walk);
    }

}
