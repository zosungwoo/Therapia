package therapia.farm.service.crop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.crop.CropEffect;
import therapia.farm.domain.crop.Effect;
import therapia.farm.repository.crop.CropEffectRepository;
import therapia.farm.repository.crop.CropRepository;
import therapia.farm.repository.crop.EffectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CropEffectService {

    @Autowired
    private CropEffectRepository cropEffectRepository;
    private CropRepository cropRepository;
    private EffectRepository effectRepository;

    public Long createCropEffect(CropEffect cropEffect){
        cropEffectRepository.save(cropEffect);
        return cropEffect.getId();
    }

    // 작물로 효능 조회
    public List<Effect> findByCropId(Long cropId){
        List<CropEffect> cropEffects = cropEffectRepository.findAllByCropId(cropId);
        List<Effect> effects = new ArrayList<>();

        for (CropEffect cropEffect: cropEffects) {
            effects.add(effectRepository.findById(cropEffect.getEffect().getId()).get());
        }
        return effects;
    }

    // 효능으로 작물 조회
    public List<Crop> findByEffectId(Long effectId){
        List<CropEffect> cropEffects = cropEffectRepository.findAllByEffectId(effectId);
        List<Crop> crops = new ArrayList<>();

        for (CropEffect cropEffect: cropEffects) {
            crops.add(cropRepository.findById(cropEffect.getCrop().getId()).get());
        }
        return crops;
    }
}
