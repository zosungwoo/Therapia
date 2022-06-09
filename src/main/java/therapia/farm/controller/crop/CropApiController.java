package therapia.farm.controller.crop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import therapia.farm.service.crop.*;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CropApiController {
    private final CropService cropService;
    private final EffectService effectService;
    private final CropEffectService cropEffectService;
    private final RecipeService recipeService;
    private final RecipeStepService recipeStepService;

    @GetMapping("/api/crop/findall")
    public Result cropList(){
        return new Result(cropService.findCrops());
    }

    @GetMapping("/api/crop/{effectId}")
    public Result cropByEffectList(@PathVariable("effectId") Long id){
        return new Result(cropEffectService.findByEffectId(id));
    }

    @GetMapping("/api/effect/findall")
    public Result effectList(){
        return new Result(effectService.findEffects());
    }

    @GetMapping("/api/effect/{cropId}")
    public Result effectByCrop(@PathVariable("cropId") Long id){
        return new Result(cropEffectService.findByCropId(id));
    }

    @GetMapping("/api/recipe/{cropId}")
    public Result recipeByCrop(@PathVariable("cropId") Long id){
        return new Result(recipeService.findByCropId(id));
    }

    @GetMapping("/api/recipe/step/{recipeId}")
    public Result recipeStepByRecipe(@PathVariable("recipeId") Long id){
        return new Result(recipeStepService.findByRecipeId(id));
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }


}
