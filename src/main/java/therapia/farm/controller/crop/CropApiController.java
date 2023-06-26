package therapia.farm.controller.crop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import therapia.farm.service.crop.*;

@Api(tags = {"Crop API"})
@Log4j2
@RestController
@RequiredArgsConstructor
public class CropApiController {
    private final CropService cropService;
    private final EffectService effectService;
    private final CropEffectService cropEffectService;
    private final RecipeService recipeService;
    private final RecipeStepService recipeStepService;

    @ApiOperation(value = "모든 작물 가져오기", notes = "모든 작물 List 가져오기")
    @GetMapping("/api/crops")
    public Result cropList(){
        return new Result(cropService.findCrops());
    }

    @ApiOperation(value = "효능에 따른 작물 가져오기",
            notes = "효능 ID로 작물 List 가져오기\n" +
                    "'?include_effects_recipes=true' 쿼리 스트링 포함 시 효능과 레시피 포함하여 가져오기 (기존의 cropList/{effect_id}와 같은 기능)")
    @GetMapping("/api/effects/{effectId}/crops")
    public Result cropByEffectList(@PathVariable("effectId") Long id,
                                   @RequestParam(value = "include_effects_recipes", defaultValue = "false") boolean includeEffectRecipe){
        if(includeEffectRecipe)
            return new Result(cropService.findCropDto(id));
        else
            return new Result(cropEffectService.findByEffectId(id));
    }

    @ApiOperation(value = "모든 효능 가져오기", notes = "모든 효능 List 가져오기")
    @GetMapping("/api/effects")
    public Result effectList(){
        return new Result(effectService.findEffects());
    }

    @ApiOperation(value = "작물의 효능 가져오기", notes = "작물 ID로 효능 List 가져오기")
    @GetMapping("/api/crops/{cropId}/effects")
    public Result effectByCrop(@PathVariable("cropId") Long id){
        return new Result(cropEffectService.findByCropId(id));
    }


    @ApiOperation(value = "작물을 활용한 레시피 가져오기", notes = "작물 ID로 레시피 List 가져오기")
    @GetMapping("/api/crops/{cropId}/recipes")
    public Result recipeByCrop(@PathVariable("cropId") Long id){
        return new Result(recipeService.findRecipeByCropId(id));
    }

    @ApiOperation(value = "레시피의 요리 순서(Step) 가져오기", notes = "레시피 ID로 레시피의 요리 순서(Step) List로 가져오기")
    @GetMapping("/api/recipes/{recipeId}/steps")
    public Result recipeStepByRecipe(@PathVariable("recipeId") Long id){
        return new Result(recipeStepService.findByRecipeId(id));
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }


}
