package therapia.farm.dto.crop;

import lombok.AllArgsConstructor;
import lombok.Data;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.crop.Effect;
import therapia.farm.domain.crop.Recipe;

import java.util.List;

@Data
@AllArgsConstructor
public class CropDto {
    private String name;
    private String season;
    private String temperature;
    private String storage;
    private List effects;
    private List<Recipe> recipe;

    public static CropDto of (Crop c, List<Effect> effects, List<Recipe> recipe) {
        return new CropDto(
                c.getName(),
                c.getSeason(),
                c.getTemperature(),
                c.getStorage(),
                effects,
                recipe
        );
    }
}
