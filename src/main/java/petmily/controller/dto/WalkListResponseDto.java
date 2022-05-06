package petmily.controller.dto;

import lombok.Getter;
import petmily.domain.walk.Walk;

@Getter
public class WalkListResponseDto {

    private Long walkId;
    private String email;
    private int year;
    private int month;
    private int day;
    private String img;
    private float avgSpeedInKMH;
    private int distanceInMeters;
    private long timeInMillis;
    private int caloriesBurned;
    private int id;

    public WalkListResponseDto(Walk entity){
        this.walkId = entity.getWalkId();
        this.email = entity.getEmail();
        this.year = entity.getYear();
        this.month = entity.getMonth();
        this.day = entity.getDay();
        this.img = entity.getImg();
        this.avgSpeedInKMH = entity.getAvgSpeedInKMH();
        this.distanceInMeters = entity.getDistanceInMeters();
        this.timeInMillis = entity.getTimeInMillis();
        this.caloriesBurned = entity.getCaloriesBurned();
        this.id = entity.getId();
    }
}
