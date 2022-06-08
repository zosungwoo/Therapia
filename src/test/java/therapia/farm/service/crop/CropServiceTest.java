package therapia.farm.service.crop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.*;
import therapia.farm.repository.crop.EffectRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CropServiceTest {

    @Autowired
    CropEffectService cropEffectService;
    @Autowired
    CropService cropService;
    @Autowired
    EffectService effectService;
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeStepService recipeStepService;

    @Test @Rollback(false)
    public void 작물효능매핑체크() throws Exception {  // 1. 작물로 효능 조회, 2. 효능으로 작물 조회
        //given
        Crop crop1 = new Crop();
        crop1.setName("시금치");
        crop1.setSeason("6~7월");
        crop1.setTemperature("0~5도");
        crop1.setStorage("마음속에 보관한다");
        Long cropId1 = cropService.createCrop(crop1);

        Crop crop2 = new Crop();
        crop2.setName("고사리");
        crop2.setSeason("9월");
        crop2.setTemperature("0~5도");
        crop2.setStorage("냉장고에 보관한다");
        Long cropId2 = cropService.createCrop(crop2);

        Effect effect1 = new Effect();
        Effect effect2 = new Effect();
        effect1.setEffect("근력 상승");
        effect2.setEffect("모발 풍성");
        Long effectId1 = effectService.createEffect(effect1);
        Long effectId2 = effectService.createEffect(effect2);


        //when
        CropEffect cropEffect1 = new CropEffect();
        cropEffect1.setCrop(crop1);
        cropEffect1.setEffect(effect1);

        CropEffect cropEffect2 = new CropEffect();
        cropEffect2.setCrop(crop1);
        cropEffect2.setEffect(effect2);

        CropEffect cropEffect3 = new CropEffect();
        cropEffect3.setCrop(crop2);
        cropEffect3.setEffect(effect1);

        Long cropEffectId1 = cropEffectService.createCropEffect(cropEffect1);
        Long cropEffectId2 = cropEffectService.createCropEffect(cropEffect2);
        Long cropEffectId3 = cropEffectService.createCropEffect(cropEffect3);


        //then
        assertEquals(effectService.findEffects(), cropEffectService.findByCropId(cropId1));
        assertEquals(cropService.findCrops(), cropEffectService.findByEffectId(effectId1));
    }

    @Test @Rollback(false)
    public void 레시피조회() throws Exception{  // 특정 작물로 레시피 조회 (1. 요리 이름, 2. 순서)
        //given
        Crop crop1 = new Crop();
        crop1.setName("시금치");
        crop1.setSeason("6~7월");
        crop1.setTemperature("0~5도");
        crop1.setStorage("마음속에 보관한다");
        Long cropId1 = cropService.createCrop(crop1);

        Recipe recipe1 = new Recipe();
        recipe1.setName("시금치김치");
        recipe1.setIngredient("배추, 시금치, 고춧가루, 설탕, 소금");
        recipe1.setCrop(crop1);
        Long recipeId1 = recipeService.createRecipe(recipe1);

        RecipeStep recipeStep1 = new RecipeStep();
        recipeStep1.setRecipe(recipe1);
        recipeStep1.setStepIdx(1L);
        recipeStep1.setCooking("시금치를 씻는다");
        Long recipeStepId1 = recipeStepService.createRecipeStep(recipeStep1);

        RecipeStep recipeStep2 = new RecipeStep();
        recipeStep2.setRecipe(recipe1);
        recipeStep2.setStepIdx(2L);
        recipeStep2.setCooking("배추를 씻는다");
        Long recipeStepId2 = recipeStepService.createRecipeStep(recipeStep2);


        //when
        List<Recipe> recipes;
        List<RecipeStep> recipeSteps;

        recipes = recipeService.findByCropId(cropId1);
        recipeSteps = recipeStepService.findByRecipeId(recipeId1);


        //then
        assertEquals(Arrays.asList(recipe1), recipes);
        assertEquals(Arrays.asList(recipeStep1, recipeStep2), recipeSteps);

    }
    @Test @Rollback(false)
    public void 레시피조회2() throws Exception {  // 특정 작물로 레시피 조회 (1. 요리 이름, 2. 순서)

        System.out.println(recipeService.findByCropId(1L).get(0).getRecipeSteps());
    }
}