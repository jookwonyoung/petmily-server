package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.controller.dto.UserSaveRequestDto;
import petmily.service.user.UserService;

@RequestMapping("/user/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/save")
    public Long save(@RequestBody UserSaveRequestDto requestDto){
        return userService.save(requestDto);
    }

//    @PutMapping("/update")
//    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){
//        return userService.update(id, requestDto);
//    }
}
