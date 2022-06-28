package therapia.farm.service.crop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.crop.Effect;
import therapia.farm.domain.crop.Recipe;
import therapia.farm.dto.crop.CropDto;
import therapia.farm.repository.crop.CropRepository;

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

    @Autowired
    private EffectService effectService;

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

    public CropDto findCropDto (Long cropId) {
        List effects = cropEffectService.findEffect(cropId);
        Crop c = cropRepository.findById(cropId).get();
        List<Recipe> recipes = recipeService.findRecipeByCropId(cropId);
        CropDto cropDto = CropDto.of(c, effects, recipes);

        return cropDto;
    }


}
