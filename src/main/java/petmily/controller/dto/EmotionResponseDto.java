package petmily.controller.dto;

import java.util.ArrayList;

public class EmotionResponseDto {
    private String message;
    private String category;
    private ArrayList<Integer> crop_position;
    private Emotion emotion;

    public EmotionResponseDto(){

    }
}

class Emotion {
    public double angry;
    public double sad;
    public double happy;
}