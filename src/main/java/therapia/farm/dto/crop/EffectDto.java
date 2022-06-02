package therapia.farm.dto.crop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EffectDto {
    private String effect;

    public static EffectDto of (EffectDto e) {
        return new EffectDto(
                e.getEffect()
        );
    }
}
