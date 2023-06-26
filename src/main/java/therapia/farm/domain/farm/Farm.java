package therapia.farm.domain.farm;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    private String contents;

    public void updateReviewRating(Double reviewRating) {
        this.reviewRating = reviewRating;
    }
}
