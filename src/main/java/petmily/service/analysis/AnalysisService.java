package petmily.service.analysis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import petmily.client.FlaskTemplate;

@Service
@RequiredArgsConstructor
public class AnalysisService {
    private final FlaskTemplate template = new FlaskTemplate(new RestTemplateBuilder());

    public String detectAnimal(String path) {
        return template.requestDetectAnimal(path);
    }

    public String breedDog(String path) {
        return template.requestBreedDog(path);
    }

    public String breedCat(String path) {
        return template.requestBreedCat(path);
    }

    public String emotion(String path) {
        return template.requestEmotion(path);
    }
}
