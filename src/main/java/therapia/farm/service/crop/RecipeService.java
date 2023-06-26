package therapia.farm.service.crop;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.dto.crop.RecipeDto;
import therapia.farm.dto.crop.RecipeNotStepDto;
import therapia.farm.repository.crop.RecipeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    // 특정 작물을 이용한 레시피 조회
    public List<RecipeDto> findRecipeByCropId(Long cropId){
        return recipeRepository.findAllByCropId(cropId).stream().map(RecipeDto::new).collect(Collectors.toList());
    }

    public List<RecipeNotStepDto> findRecipeNotStepByCropId(Long cropId) {
        return recipeRepository.findAllByCropId(cropId).stream().map(RecipeNotStepDto::new).collect(Collectors.toList());
    }
}
