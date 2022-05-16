package petmily.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class EmotionResponseDto {
    private String message;
    private String category;
    private ArrayList<Integer> crop_position;
    private Emotion emotion;

    public EmotionResponseDto(){

    }
}

