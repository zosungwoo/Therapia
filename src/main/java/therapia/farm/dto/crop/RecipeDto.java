package therapia.farm.dto.crop;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipeDto {
    private String name;
    private String ingredient;
    private List<RecipeDto> recipeList;

    public static RecipeDto of (RecipeDto r, List<RecipeDto> recipeDtoList) {
        return new RecipeDto(
                r.getName(),
                r.getIngredient(),
                recipeDtoList
        );
    }
}
