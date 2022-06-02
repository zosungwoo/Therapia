package therapia.farm.dto.farm;

import lombok.AllArgsConstructor;
import lombok.Data;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.FarmCategory;

import java.util.List;

@Data
@AllArgsConstructor
public class FarmDto {
    private String name;
    private FarmCategory category;
    private Double reviewRating;
    private String phone;
    private String address;
    private String placeUrl;
    private Double location_x;
    private Double location_y;
    private List<ReviewDto> reviewList;

    public static FarmDto of (Farm f, List<ReviewDto> reviewDtoList) {
        return new FarmDto(
                f.getName(),
                f.getCategory(),
                f.getReviewRating(),
                f.getPhone(),
                f.getAddress(),
                f.getPlaceUrl(),
                f.getLocation_x(),
                f.getLocation_y(),
                reviewDtoList
        );
    }
}
