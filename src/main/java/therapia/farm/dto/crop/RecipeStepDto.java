package therapia.farm.dto.crop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeStepDto {
    private Long stepIdx;
    private String cooking;

    public static RecipeStepDto of (RecipeStepDto r) {
        return new RecipeStepDto(
                r.getStepIdx(),
                r.getCooking()
        );
    }
}
