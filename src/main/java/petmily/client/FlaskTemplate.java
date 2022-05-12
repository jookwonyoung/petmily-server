package petmily.client;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class FlaskTemplate {

    @Autowired
    private final RestTemplate restTemplate;
    String url = "http://wolfwork.iptime.org:34343/";

    public FlaskTemplate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    // 개와 고양이의 포함 여부
    public String postDetectAnimal(File imageFile) {
        String apiUrl = url + "detect";

        return postRequest(imageFile, apiUrl);
    }

    public String postDetectAnimal(String filePath) {
        String apiUrl = url + "detect";

        return postRequest(filePath, apiUrl);
    }


    // 개의 종 분류
    public String postBreedDog(File imageFile) {
        String apiUrl = url + "predict/breed/dog";

        return postRequest(imageFile, apiUrl);
    }
    public String postBreedDog(String filePath) {
        String apiUrl = url + "predict/breed/dog";

        return postRequest(filePath, apiUrl);
    }

    public String postBreedCat(File imageFile) {
        String apiUrl = url + "predict/breed/cat";

        return postRequest(imageFile, apiUrl);
    }
    public String postBreedCat(String filePath) {
        String apiUrl = url + "predict/breed/cat";

        return postRequest(filePath, apiUrl);
    }

    // 감정 분류
    public String postEmotion(File imageFile) {
        String apiUrl = url + "predict/emotion";

        return postRequest(imageFile, apiUrl);
    }
    public String postEmotion(String filePath) {
        String apiUrl = url + "predict/emotion";

        return postRequest(filePath, apiUrl);
    }

    @Nullable
    private String postRequest(File imageFile, String apiUrl) {
        // write header part
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // write body part
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>(1);
        body.add("file", new FileSystemResource(imageFile));

        // write HTTPEntity instance
        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body, headers);

        // send request
        return restTemplate.postForObject(apiUrl, requestEntity, String.class);
    }

    @Nullable
    private String postRequest(String filePath, String apiUrl) {
        // write header part
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // write body part
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>(1);
        body.add("file", filePath);

        // write HTTPEntity instance
        HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<>(body, headers);

        // send request
        return restTemplate.postForObject(apiUrl, requestEntity, String.class);
    }
}
