package therapia.farm.dto.crop;

import lombok.Getter;
import therapia.farm.domain.crop.RecipeStep;
@Getter
public class RecipeStepDto {
    private final Long id;
    private final Long stepIdx;
    private final String cooking;

    public RecipeStepDto(RecipeStep recipeStep) {
        this.id = recipeStep.getId();
        this.stepIdx = recipeStep.getStepIdx();
        this.cooking = recipeStep.getCooking();
    }
}
