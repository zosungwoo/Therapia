package therapia.farm.service.crop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.RecipeStep;
import therapia.farm.domain.farm.repository.crop.RecipeStepRepository;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class RecipeStepService {

    @Autowired
    private RecipeStepRepository recipeStepRepository;

    public Long createRecipeStep(RecipeStep recipeStep){
        recipeStepRepository.save(recipeStep);
        return recipeStep.getId();
    }

    // 레시피 아이디로 레시피 순서 조회
    public List<RecipeStep> findByRecipeId(Long recipeId){
        List<RecipeStep> tmp = recipeStepRepository.findAllByRecipeId(recipeId);

        RecipeStep []recipeSteps = new RecipeStep[tmp.size()];  // 레시피 순서에 맞게 배열하는 과정
        int idx;
        for(RecipeStep recipeStep: tmp){
            idx = recipeStep.getStepIdx().intValue()-1;
            recipeSteps[idx] = recipeStep;
        }
        return Arrays.asList(recipeSteps);
    }
}
