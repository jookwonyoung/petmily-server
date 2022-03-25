package petmily.domain.places;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 자동 할당
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String phone;

    @Column(length = 500, nullable = false)
    private String address;

    @Column(length = 500, nullable = false)
    private String url;

    @Column(length = 500, nullable = false)
    private String categories;

    @Builder
    public Places(String name, String phone, String address, String url, String categories){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.url = url;
        this.categories = categories;
    }

}
