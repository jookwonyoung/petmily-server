package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petmily.config.auth.LoginUser;
import petmily.config.auth.dto.SessionUser;
import petmily.controller.dto.WalkListResponseDto;
import petmily.controller.dto.WalkSaveRequestDto;
import petmily.service.walk.WalkService;

import java.util.List;


@RequestMapping("/api/walk")
@RequiredArgsConstructor
@RestController
public class WalkApiController {

    private final WalkService walkService;

    @PostMapping("/save")
    public Long save(@RequestBody WalkSaveRequestDto requestDto, @LoginUser SessionUser user){
        requestDto.setEmail(user.getEmail());
        return walkService.save(requestDto);
    }

    @GetMapping("/findAll")
    public List<WalkListResponseDto> findAll(@LoginUser SessionUser user){
        return walkService.findAllDesc(user.getEmail());
    }

    @DeleteMapping("/delete/{year}/{month}/{day}")
    public void delete(@PathVariable int year, @PathVariable int month,@PathVariable int day ,@LoginUser SessionUser user) {
        walkService.delete(year, month, day, user.getEmail());
    }
}
