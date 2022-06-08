package therapia.farm.controller.crop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.crop.Effect;
import therapia.farm.domain.crop.RecipeStep;
import therapia.farm.service.crop.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CropApiController {
    private final CropService cropService;
    private final EffectService effectService;
    private final CropEffectService cropEffectService;
    private final RecipeService recipeService;
    private final RecipeStepService recipeStepService;

    @GetMapping("/crop")
    public Result cropList(){
        return new Result(cropService.findCrops());
    }

    @GetMapping("/crop/{effectId}")
    public Result cropByEffectList(@PathVariable("effectId") Long id){
        return new Result(cropEffectService.findByEffectId(id));
    }

    @GetMapping("/effect")
    public Result effectList(){
        return new Result(effectService.findEffects());
    }

    @GetMapping("/effect/{cropId}")
    public Result effectByCrop(@PathVariable("cropId") Long id){
        return new Result(cropEffectService.findByCropId(id));
    }

    @GetMapping("/recipe/{cropId}")
    public Result recipeByCrop(@PathVariable("cropId") Long id){
        return new Result(recipeService.findByCropId(id));
    }

    @GetMapping("/recipe/step/{recipeId}")
    public Result recipeStepByRecipe(@PathVariable("recipeId") Long id){
        return new Result(recipeStepService.findByRecipeId(id));
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }


}
