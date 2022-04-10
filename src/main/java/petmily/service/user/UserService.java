package petmily.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.controller.dto.UserSaveRequestDto;
import petmily.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto requestDto){
        return userRepository.save(requestDto.toEntity()).getUserId();
    }
}
