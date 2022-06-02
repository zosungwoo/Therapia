package therapia.farm.dto.farm;

import lombok.AllArgsConstructor;
import lombok.Data;
import therapia.farm.domain.farm.Review;

@Data
@AllArgsConstructor
public class ReviewDto {
    private String nickname;
    private String title;
    private String contents;
    private Double rating;

    public static ReviewDto of (Review r) {
        return new ReviewDto(
                r.getNickname(),
                r.getTitle(),
                r.getContents(),
                r.getRating()
        );
    }

}
