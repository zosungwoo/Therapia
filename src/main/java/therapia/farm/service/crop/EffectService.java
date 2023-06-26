package therapia.farm.service.crop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.CropEffect;
import therapia.farm.dto.crop.*;
import therapia.farm.repository.crop.CropEffectRepository;
import therapia.farm.repository.crop.EffectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EffectService {
    private final EffectRepository effectRepository;
    private final CropEffectRepository cropEffectRepository;
    private final CropEffectService cropEffectService;
    // 모든 효능 조회
    public List<EffectDto> findEffects(){
        return effectRepository.findAll().stream().map(EffectDto::new).collect(Collectors.toList());  // 페이징도 가능
    }
    //효능 ID로 작물 List 가져오기(효능과 레시피까지 가져오기)
    public List<CropEffectDto> findCropEffect (Long effectId) {
        List<CropEffect> cropList = cropEffectRepository.findAllByEffectId(effectId);
        List<CropEffectDto> result = new ArrayList<>();
        for(CropEffect c:cropList) {
            List<String> effectDtoList = cropEffectService.findEffectsByCropId(c.getCrop().getId());
            result.add(new CropEffectDto(c, effectDtoList));
        }

        return result;
    }
}
