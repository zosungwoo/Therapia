package therapia.farm.service.crop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.crop.CropEffect;
import therapia.farm.domain.crop.Effect;
import therapia.farm.dto.crop.CropDto;
import therapia.farm.dto.crop.EffectDto;
import therapia.farm.repository.crop.CropEffectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CropEffectService {
    private final CropEffectRepository cropEffectRepository;
    // 작물로 증상 조회
    public List<EffectDto> findByCropId(Long cropId){
        List<CropEffect> cropEffects = cropEffectRepository.findAllByCropId(cropId);
        List<Effect> effects = new ArrayList<>();
        for(CropEffect c: cropEffects) {
            effects.add(c.getEffect());
        }
        return effects.stream().map(EffectDto::new).collect(Collectors.toList());
    }

    // 증상으로 작물 조회
    public List<CropDto> findByEffectId(Long effectId){
        List<CropEffect> cropEffects = cropEffectRepository.findAllByEffectId(effectId);
        List<Crop> crops = new ArrayList<>();
        for(CropEffect c: cropEffects) {
            crops.add(c.getCrop());
        }
        return crops.stream().map(CropDto::new).collect(Collectors.toList());
    }

    //작물로 효능조회
    public List<String> findEffectsByCropId(Long cropId){
        List<CropEffect> cropEffects = cropEffectRepository.findAllByCropId(cropId);
        List<String> effects = new ArrayList<>();
        for(CropEffect c: cropEffects) {
            effects.add(c.getEffect().getEffect());
        }
        return effects;
    }
}
