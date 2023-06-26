package therapia.farm.domain.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;
    private String title;
    private String contents;
    private Double rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    @JsonIgnore
    private Farm farm;
    @Builder
    public Review(String title, String contents, Double rating, Member member, Farm farm) {
        this.title = title;
        this.contents = contents;
        this.rating = rating;
        this.member = member;
        this.farm = farm;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContents(String contents) {
        this.contents = contents;
    }
    public void updateRating(Double rating) {
        this.rating = rating;
    }
}
