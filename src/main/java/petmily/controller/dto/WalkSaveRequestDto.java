package petmily.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petmily.domain.walk.Walk;

@Getter
@Setter
@NoArgsConstructor
public class WalkSaveRequestDto {
    private String email;
    private int year;
    private int month;
    private int day;
    private long time;
    private int distance;
    private float speed;
    private int calories;
    private String walkImg;

    @Builder
    public WalkSaveRequestDto(String email, int year, int month, int day, long time, int distance, float speed, int calories, String walkImg){
        this.email = email;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.distance = distance;
        this.speed = speed;
        this.calories = calories;
        this.walkImg = walkImg;
    }

    public Walk toEntity() {
        return Walk.builder()
                .email(email)
                .year(year)
                .month(month)
                .day(day)
                .time(time)
                .distance(distance)
                .speed(speed)
                .calories(calories)
                .walkImg(walkImg)
                .build();
    }

}
