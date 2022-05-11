package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petmily.controller.dto.WalkListResponseDto;
import petmily.controller.dto.WalkSaveRequestDto;
import petmily.service.walk.WalkService;

import java.io.File;
import java.util.List;


@RequestMapping("/api/walk")
@RequiredArgsConstructor
@RestController
public class WalkApiController {

    private final WalkService walkService;

    @PostMapping("/save")
    public Long save(@RequestHeader(value="email") String email, @RequestParam("year") int year,
                     @RequestParam("month") int month, @RequestParam("day") int day,
                     @RequestParam("img") MultipartFile files, @RequestParam("avgSpeedInKMH") float avgSpeedInKMH,
                     @RequestParam("distanceInMeters") int distanceInMeters, @RequestParam("timeInMillis") long timeInMillis,
                     @RequestParam("caloriesBurned") int caloriesBurned, @RequestParam("id") int id){

        WalkSaveRequestDto requestDto = new WalkSaveRequestDto();
        requestDto.setEmail(email);
        requestDto.setYear(year);
        requestDto.setMonth(month);
        requestDto.setDay(day);
        requestDto.setAvgSpeedInKMH(avgSpeedInKMH);
        requestDto.setCaloriesBurned(caloriesBurned);
        requestDto.setDistanceInMeters(distanceInMeters);
        requestDto.setId(id);
        requestDto.setTimeInMillis(timeInMillis);
        Long walkId = walkService.save(requestDto);

        String rootPath;
        if(new File("/home/ec2-user/petmilyServer/step1/imgDB/walk").exists()){
            rootPath = "/home/ec2-user/petmilyServer/step1/imgDB/walk";        //ec2-server
        }else{
            rootPath = "/Users/jookwonyoung/Documents/petmily/testImg/walk";     //localhost
        }

        String emailPath = rootPath + "/" + email;
        String conType = files.getContentType();
        if(!(conType.equals("image/png") || conType.equals("image/jpeg"))){
            Long error = null;
            return error;
        }
        if (!new File(emailPath).exists()) {
            try{
                new File(emailPath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        String filePath = emailPath + "/" + walkId;
        try {
            files.transferTo(new File(filePath));
        }catch(Exception e) {
            e.printStackTrace();
        }

        return walkId;
    }

    @GetMapping("findAll")
    public List<WalkListResponseDto> findAll(@RequestHeader(value="email") String email){
        return walkService.findAllDesc(email);
    }

    @GetMapping("/findByDate/{year}/{month}/{day}")
    public List<WalkListResponseDto> findByDate(@RequestHeader(value="email") String email, @PathVariable int year, @PathVariable int month,@PathVariable int day){
        return walkService.findDateDesc(year, month, day, email);
    }

    @DeleteMapping("/delete/{id}/{year}/{month}/{day}")
    public void delete(@RequestHeader(value="email") String email, @PathVariable int id,@PathVariable int year, @PathVariable int month,@PathVariable int day) {
        walkService.delete(id, year, month, day, email);
    }
}
