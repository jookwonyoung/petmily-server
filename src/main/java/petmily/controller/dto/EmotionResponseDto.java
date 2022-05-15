package petmily.controller.dto;

import java.util.HashMap;

public class EmotionResponseDto {
    private String category;
    private HashMap<String,Integer> crop_position = new HashMap<String,Integer>();
    private HashMap<String,Integer> emotion = new HashMap<String,Integer>();

    public EmotionResponseDto(){

    }
}
