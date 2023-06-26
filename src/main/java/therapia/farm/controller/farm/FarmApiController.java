package therapia.farm.controller.farm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.FarmCategory;
import therapia.farm.service.farm.FarmService;

import javax.xml.transform.Result;

@Api(tags = {"Farm API"})
@Log4j2
@RestController
@RequiredArgsConstructor
public class FarmApiController {
    private final FarmService farmService;

    @ApiOperation(value = "모든 농장 가져오기", notes = "모든 농장 List 가져오기")
    @GetMapping("/api/farms")
    public Result farmList() {
        return new Result(farmService.findFarms());
    }

    @ApiOperation(value = "카테고리별 농장 가져오기", notes = "카테고리 값으로 농장 List 가져오기")
    @GetMapping("/api/farms/category/{category}")
    public Result farmByCategory(@PathVariable("category")FarmCategory farmCategory) {
        return new Result(farmService.findByCategory(farmCategory));
    }

    @ApiOperation(value = "농장 가져오기", notes = "농장 ID로 농장 가져오기")
    @GetMapping("/api/farms/{farmId}")
    public Farm farmById(@PathVariable("farmId")Long farm_Id) {
        return farmService.findById(farm_Id);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
