package petmily.controller.dto;

import lombok.Getter;
import petmily.domain.walk.Walk;

@Getter
public class WalkResponseDto {

    private Long walkId;
    private int num;
    private String email;
    private int year;
    private int month;
    private int day;
    private long time;
    private int distance;
    private float speed;
    private int calories;
    private String walkImg;

    public WalkResponseDto(Walk entity){
        this.walkId = entity.getWalkId();
        this.num = entity.getNum();
        this.email = entity.getEmail();
        this.year = entity.getYear();
        this.month = entity.getMonth();
        this.day = entity.getDay();
        this.time = entity.getTime();
        this.distance = entity.getDistance();
        this.speed = entity.getSpeed();
        this.calories = entity.getCalories();
        this.walkImg = entity.getWalkImg();
    }

}
