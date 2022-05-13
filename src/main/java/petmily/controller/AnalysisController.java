package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import petmily.service.analysis.AnalysisService;

import java.io.File;

@RequestMapping("/api/analysis")
@RequiredArgsConstructor
@RestController
public class AnalysisController {

    private final AnalysisService analysisService;


    private String ec2Path = "/home/ec2-user/petmilyServer/step1/imgDB/tmp";

    @GetMapping("/breed/dog")
    public String breedDog(@RequestParam("img") MultipartFile img) {



        String conType = img.getContentType();
        if (!(conType.equals("image/png") || conType.equals("image/jpeg"))) {
            return "not valid image";
        }

        String filePath = ec2Path + "/" + System.currentTimeMillis();
        try {
            img.transferTo(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result =  analysisService.breedDog(filePath);

        try {
            File file = new File(filePath);
            if (file.exists()) {
                if(file.delete()) {
                    System.out.println("파일삭제 성공");
                } else {
                    System.out.println("파일삭제 실패");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @GetMapping("/breed/cat")
    public String breedCat(@RequestParam("img") String img) {
        return analysisService.breedCat(img);

    }

    @GetMapping("/emotion")
    public String emotion(@RequestParam("img") String img) {
        return analysisService.emotion(img);

    }

}
