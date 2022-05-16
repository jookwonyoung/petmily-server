package petmily.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emotion {
    public double angry;
    public double sad;
    public double happy;


    public Emotion(double angry, double sad, double happy) {
        this.angry = angry;
        this.sad = sad;
        this.happy = happy;
    }
}
