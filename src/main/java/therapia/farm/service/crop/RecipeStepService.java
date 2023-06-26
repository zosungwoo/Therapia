package therapia.farm.service.crop;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.RecipeStep;
import therapia.farm.dto.crop.RecipeStepDto;
import therapia.farm.repository.crop.RecipeStepRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeStepService {
    private final RecipeStepRepository recipeStepRepository;
    // 레시피 아이디로 레시피 순서 조회
    public List<RecipeStepDto> findByRecipeId(Long recipeId){
        List<RecipeStep> result = recipeStepRepository.findAllByRecipeId(recipeId);
        return result.stream().map(RecipeStepDto::new).collect(Collectors.toList());
    }
}
