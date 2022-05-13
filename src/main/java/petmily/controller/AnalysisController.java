package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import petmily.service.analysis.AnalysisService;

@RequestMapping("/api/analysis")
@RequiredArgsConstructor
@RestController
public class AnalysisController {

    private final AnalysisService analysisService;

    private String ec2Path = "/home/ec2-user/petmilyServer/step1/imgDB/temp";

    @GetMapping("/breed/dog")
    public String breedDog(@RequestParam("img") String img) {
        return analysisService.breedDog(img);

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
