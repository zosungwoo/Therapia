package therapia.farm.dto.farm;

import lombok.Getter;
import therapia.farm.domain.farm.Review;

@Getter
public class ReviewResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final Double rating;
    private final Long farmId;
    private final String nickname;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.contents = review.getContents();
        this.rating = review.getRating();
        this.farmId = review.getFarm().getId();
        this.nickname = review.getMember().getNickname();
    }
}
