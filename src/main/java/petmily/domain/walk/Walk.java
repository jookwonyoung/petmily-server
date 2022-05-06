package petmily.domain.walk;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 자동 할당
    private Long walkId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private int num;

    private int year;
    private int month;
    private int day;
    private long time;
    private int distance;
    private float speed;
    private int calories;
    private String walkImg;


    @Builder
    public Walk(int num, String email, int year, int month, int day, long time, int distance, float speed, int calories, String walkImg){
        this.num = num;
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

}
