package therapia.farm.domain.farm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Farm {
    @Id @GeneratedValue
    @Column(name = "farm_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FarmCategory category;

    private String name;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private Double reviewRating;

    private String phone;

    private String address;

    private String placeUrl;

    private Double location_x;

    private Double location_y;


    // 농장 rating 업데이트 메서드 (리뷰가 추가/삭제 될 때 실행) -> 리뷰 서비스에서 해야할듯 ㅇㅇ
//    public void updateReviewRating(Double reviewRating){
//        this.reviewRating = reviewRating;
//    }


    public void updateReviewRating(){
        Double rating = 0.0;
        int i = 0;
        for(Review review : reviews){
            rating += review.getRating();
            i += 1;
        }
        if(i != 0)
            reviewRating = rating / i;
    }
}
