package therapia.farm.dto.crop;

import lombok.Getter;
import therapia.farm.domain.crop.Recipe;
@Getter
public class RecipeNotStepDto {
    private final Long id;
    private final String name;
    private final String ingredient;
    public RecipeNotStepDto(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.ingredient = recipe.getIngredient();
    }
}
