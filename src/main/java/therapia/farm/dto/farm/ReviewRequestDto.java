package therapia.farm.dto.farm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.Member;
import therapia.farm.domain.farm.Review;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
    private String title;
    private String contents;
    private Double rating;

    @Builder ReviewRequestDto(String title, String contents, Double rating) {
        this.title = title;
        this.contents = contents;
        this.rating = rating;
    }

    public static Review toEntity(ReviewRequestDto requestDto, Member member, Farm farm) {
        return Review.builder()
                .title(requestDto.title)
                .contents(requestDto.contents)
                .rating(requestDto.rating)
                .member(member)
                .farm(farm)
                .build();
    }
}
