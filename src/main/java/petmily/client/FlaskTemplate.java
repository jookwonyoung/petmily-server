package petmily.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FlaskTemplate {

    private final RestTemplate restTemplate;
    String url = "http://wolfwork.iptime.org:34343/";

    // RestTemplate using builder
    @Autowired
    public FlaskTemplate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    // 개와 고양이의 포함 여부
    public String postDetectAnimal(File imageFile) {
        String apiUrl = url + "detect";

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

    // 개의 종 분류
    public String postBreedDog(File imageFile) {
        String apiUrl = url + "predict/breed/dog";

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

    // 고양이의 종 분류
    public String postBreedCat(File imageFile) {
        String apiUrl = url + "predict/breed/cat";

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

    // 감정 분류
    public String postEmotion(File imageFile) {
        String apiUrl = url + "predict/emotion";

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
}
