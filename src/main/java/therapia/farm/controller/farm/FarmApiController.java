package therapia.farm.controller.farm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.FarmCategory;
import therapia.farm.dto.farm.FarmDto;
import therapia.farm.service.farm.FarmService;

import javax.xml.transform.Result;
import java.util.List;

@Api(tags = {"Farm API"})
@Log4j2
@RestController
@RequiredArgsConstructor
public class FarmApiController {
    private final FarmService farmService;

    @ApiOperation(value = "모든 농장 가져오기", notes = "모든 농장 List 가져오기")
    @GetMapping("/api/farms")
    public ResponseEntity<List<FarmDto>> farmList() {
        return new ResponseEntity<>(farmService.findFarms(), HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리별 농장 가져오기", notes = "카테고리 값으로 농장 List 가져오기")
    @GetMapping("/api/farms/category/{category}")
    public ResponseEntity<?> farmByCategory(@PathVariable("category")FarmCategory farmCategory) {
        return new ResponseEntity<>(farmService.findByCategory(farmCategory), HttpStatus.OK);
    }

    @ApiOperation(value = "농장 가져오기", notes = "농장 ID로 농장 가져오기")
    @GetMapping("/api/farms/{farmId}")
    public ResponseEntity<?> farmById(@PathVariable("farmId")Long farm_Id) {
        return new ResponseEntity<>(farmService.findById(farm_Id), HttpStatus.OK);
    }
}
