package therapia.farm.controller.crop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import therapia.farm.dto.crop.CropDto;
import therapia.farm.dto.crop.EffectDto;
import therapia.farm.dto.crop.RecipeDto;
import therapia.farm.dto.crop.RecipeStepDto;
import therapia.farm.service.crop.*;

import java.util.List;

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
    public ResponseEntity<List<CropDto>> cropList(){
        return new ResponseEntity<>(cropService.findCrops(), HttpStatus.OK);
    }

    @ApiOperation(value = "효능에 따른 작물 가져오기",
            notes = "효능 ID로 작물 List 가져오기\n" +
                    "'?include_effects=true' 쿼리 스트링 포함 시 효능 포함하여 가져오기 (기존의 cropList/{effect_id}와 같은 기능)")
    @GetMapping("/api/effects/{effectId}/crops")
    public ResponseEntity<?> cropByEffectList(@PathVariable("effectId") Long id,
                                   @RequestParam(value = "include_effects", defaultValue = "false") boolean includeEffect){
        if(includeEffect)
            return new ResponseEntity<>(effectService.findCropEffect(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(cropEffectService.findByEffectId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "모든 효능 가져오기", notes = "모든 효능 List 가져오기")
    @GetMapping("/api/effects")
    public ResponseEntity<List<EffectDto>> effectList(){
        return new ResponseEntity<>(effectService.findEffects(), HttpStatus.OK);
    }

    @ApiOperation(value = "작물의 효능 가져오기", notes = "작물 ID로 효능 List 가져오기")
    @GetMapping("/api/crops/{cropId}/effects")
    public ResponseEntity<List<EffectDto>> effectByCrop(@PathVariable("cropId") Long id){
        return new ResponseEntity<>(cropEffectService.findByCropId(id), HttpStatus.OK);
    }


    @ApiOperation(value = "작물을 활용한 레시피 가져오기", notes = "작물 ID로 레시피 List 가져오기")
    @GetMapping("/api/crops/{cropId}/recipes")
    public ResponseEntity<List<RecipeDto>> recipeByCrop(@PathVariable("cropId") Long id){
        return new ResponseEntity<>(recipeService.findRecipeByCropId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "레시피의 요리 순서(Step) 가져오기", notes = "레시피 ID로 레시피의 요리 순서(Step) List로 가져오기")
    @GetMapping("/api/recipes/{recipeId}/steps")
    public ResponseEntity<List<RecipeStepDto>> recipeStepByRecipe(@PathVariable("recipeId") Long id){
        return new ResponseEntity<>(recipeStepService.findByRecipeId(id), HttpStatus.OK);
    }
}
