package therapia.farm.service.farm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.FarmCategory;
import therapia.farm.dto.farm.FarmDto;
import therapia.farm.exception.CustomException;
import therapia.farm.repository.farm.FarmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    // 모든 농장 조회
    public List<FarmDto> findFarms(){
        return farmRepository.findAll().stream().map(FarmDto::new).collect(Collectors.toList());
    }

    // 단건 조회
    public FarmDto findById(Long farmId){
        if (farmRepository.findById(farmId).isEmpty()) {
            throw new CustomException("존재하지 않는 농장");
        }
        return new FarmDto(farmRepository.findById(farmId).get());
    }

    //카테고리가 같은 농장 조회
    public List<FarmDto> findByCategory(FarmCategory farmCategory) {
        if (farmRepository.findAllByCategory(farmCategory).size() == 0) {
            throw new CustomException("존재하지 않는 카테고리");
        }
        return farmRepository.findAllByCategory(farmCategory).stream().map(FarmDto::new).collect(Collectors.toList());
    }

}
