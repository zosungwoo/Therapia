package therapia.farm.dto.crop;

import lombok.Getter;
import therapia.farm.domain.crop.Effect;

@Getter
public class EffectDto {
    private final Long id;
    private final String symptom;
    private final String effect;

    public EffectDto(Effect effect) {
        this.id = effect.getId();
        this.symptom = effect.getSymptom();
        this.effect = effect.getEffect();
    }
}
