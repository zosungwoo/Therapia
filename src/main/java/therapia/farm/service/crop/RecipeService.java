package therapia.farm.service.crop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.Recipe;
import therapia.farm.repository.crop.RecipeRepository;

import java.util.List;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Long createRecipe(Recipe recipe){
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    // 단건 레시피 조회
    public Recipe findOne(Long recipeId){
        return recipeRepository.findById(recipeId).get();
    }

    // 모든 레시피 조회
    public List<Recipe> findRecipes(){
        return recipeRepository.findAll();
    }

    // 특정 작물을 이용한 레시피 조회
    public List<Recipe> findRecipeByCropId(Long cropId){
        return recipeRepository.findAllByCropId(cropId);
    }
}
