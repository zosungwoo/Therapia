package therapia.farm.dto.crop;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CropDto {
    private String name;
    private String season;
    private String temperature;
    private String storage;
    private List<EffectDto> effectList;
    private List<RecipeDto> recipeList;

    public static CropDto of (CropDto c, List<EffectDto> effectDtoList, List<RecipeDto> recipeDtoList) {
        return new CropDto(
                c.getName(),
                c.getSeason(),
                c.getTemperature(),
                c.getStorage(),
                effectDtoList,
                recipeDtoList
        );
    }
}
