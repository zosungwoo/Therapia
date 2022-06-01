package therapia.farm.service.crop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.crop.Effect;
import therapia.farm.repository.crop.EffectRepository;

import java.util.List;

@Service
@Transactional
public class EffectService {

    @Autowired
    private EffectRepository effectRepository;

    public Long createEffect(Effect effect){
        effectRepository.save(effect);
        return effect.getId();
    }

    public Effect findOne(Long effectId){
        return effectRepository.findById(effectId).get();
    }

    // 모든 효능 조회
    public List<Effect> findEffects(){
        return effectRepository.findAll();  // 페이징도 가능
    }

}
