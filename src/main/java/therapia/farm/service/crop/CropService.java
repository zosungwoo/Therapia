package therapia.farm.service.crop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.crop.Effect;
import therapia.farm.domain.crop.Recipe;
import therapia.farm.dto.crop.CropDto;
import therapia.farm.repository.crop.CropEffectRepository;
import therapia.farm.repository.crop.CropRepository;
import therapia.farm.repository.crop.EffectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private CropEffectService cropEffectService;

    @Autowired
    private RecipeService recipeService;

    public Long createCrop(Crop crop){
        cropRepository.save(crop);
        return crop.getId();
    }

    public Crop findOne(Long cropId){
        return cropRepository.findById(cropId).get();
    }

    // 모든 작물 조회
    public List<Crop> findCrops(){
        return cropRepository.findAll();  // 페이징도 가능
    }

    public List<CropDto> findCropDto (Long effectId) {
        List<Crop> cropList = cropEffectService.findByEffectId(effectId);
        List<CropDto> cropDtoList = new ArrayList<>();
        cropList.forEach(c -> {
            List<Effect> effects = cropEffectService.findByCropId(c.getId());
            List<Recipe> recipes = recipeService.findRecipeByCropId(c.getId());
            CropDto cd = CropDto.of(c,effects,recipes);
            cropDtoList.add(cd);
        });

        return cropDtoList;
    }


}
