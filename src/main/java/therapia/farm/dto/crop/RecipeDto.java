package therapia.farm.dto.crop;

import lombok.Getter;
import therapia.farm.domain.crop.Recipe;
import therapia.farm.domain.crop.RecipeStep;

import java.util.List;
@Getter
public class RecipeDto {
    private final Long id;
    private final String name;
    private final String ingredient;
    private final List<RecipeStep> recipeSteps;

    public RecipeDto(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.ingredient = recipe.getIngredient();
        this.recipeSteps = recipe.getRecipeSteps();
    }
}
