package therapia.farm.dto.farm;

import lombok.Getter;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.FarmCategory;
import therapia.farm.domain.farm.Review;

import java.util.List;
@Getter
public class FarmDto {
    private final Long id;
    private final FarmCategory category;
    private final String name;
    private final List<Review> reviews;
    private final Double reviewRating;
    private final String phone;
    private final String address;
    private final String placeUrl;
    private final Double location_x;
    private final Double location_y;
    private final String contents;

    public FarmDto(Farm farm) {
        this.id = farm.getId();
        this.category = farm.getCategory();
        this.name = farm.getName();;
        this.reviews = farm.getReviews();
        this.reviewRating = farm.getReviewRating();
        this.phone = farm.getPhone();
        this.address = farm.getAddress();
        this.placeUrl = farm.getPlaceUrl();
        this.location_x = farm.getLocation_x();
        this.location_y = farm.getLocation_y();
        this.contents = farm.getContents();
    }
}
