package petmily.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FlaskTemplateTest extends TestCase {

    public void testPostDetectAnimal() throws IOException {
        File file = new File("C:\\Users\\kongdol\\Pictures\\index.jpg");
        FlaskTemplate flaskTemplate = new FlaskTemplate(new RestTemplateBuilder());
        String result = flaskTemplate.postDetectAnimal(file);
        System.out.println(result);

        // Json to Object Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(result);
        System.out.println("결과: " + node.get("detected").asText());
    }

    public void testPostBreedDog() {
        File file = new File("C:\\Users\\kongdol\\Pictures\\index.jpg");
        FlaskTemplate flaskTemplate = new FlaskTemplate(new RestTemplateBuilder());
        String result = flaskTemplate.postBreedDog(file);
        System.out.println(result);
    }

    public void testPostBreedCat() {
        File file = new File("C:\\Users\\kongdol\\Pictures\\index.jpg");
        FlaskTemplate flaskTemplate = new FlaskTemplate(new RestTemplateBuilder());
        String result = flaskTemplate.postBreedCat(file);
        System.out.println(result);
    }

    public void testPostEmotion() {
        File file = new File("C:\\Users\\kongdol\\Pictures\\index.jpg");
        FlaskTemplate flaskTemplate = new FlaskTemplate(new RestTemplateBuilder());
        String result = flaskTemplate.postEmotion(file);
        System.out.println(result);
    }
}