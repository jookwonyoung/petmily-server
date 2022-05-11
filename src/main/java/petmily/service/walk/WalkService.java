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
        Long id = walkRepository.save(requestDto.toEntity()).getWalkId();
        Walk walk = walkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        walk.update(id);
        return id;
    }

    @Transactional(readOnly = true)
    public List<WalkListResponseDto> findAllDesc(String email){
        return walkRepository.findAllDesc(email).stream()
                .map(WalkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<WalkListResponseDto> findEmailDesc(int year, int month, int day, String email){
        return walkRepository.findEmailDesc(year, month, day, email).stream()
                .map(WalkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(int id, int year, int month, int day, String email) {
        Walk walk = walkRepository.findByEmail(id, year, month, day, email);
        walkRepository.delete(walk);
    }

}
