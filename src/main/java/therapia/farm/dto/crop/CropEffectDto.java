package therapia.farm.dto.crop;

import lombok.Getter;
import therapia.farm.domain.crop.CropEffect;

import java.util.List;
@Getter
public class CropEffectDto {
    private final String name;
    private final List<String> effects;

    public CropEffectDto(CropEffect cropEffect, List<String> effectDto) {
        this.name = cropEffect.getCrop().getName();
        this.effects = effectDto;
    }
}
