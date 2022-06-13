package therapia.farm.controller.farm;

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

@Log4j2
@RestController
@RequiredArgsConstructor
public class FarmApiController {
    private final FarmService farmService;

    @GetMapping("/api/farm/findall")
    public Result farmList() {
        return new Result(farmService.findFarms());
    }

    @GetMapping("/api/farm/{category}")
    public Result farmByCategory(@PathVariable("category")FarmCategory farmCategory) {
        return new Result(farmService.findByCategory(farmCategory));
    }

    @GetMapping("/api/farm/findone/{farmId}")
    public Farm farmById(@PathVariable("farmId")Long farm_Id) {
        return farmService.findById(farm_Id);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
