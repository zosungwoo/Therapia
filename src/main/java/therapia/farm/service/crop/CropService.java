package therapia.farm.service.crop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.dto.crop.CropDto;
import therapia.farm.dto.farm.FarmDto;
import therapia.farm.exception.CustomException;
import therapia.farm.repository.crop.CropRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CropService {
    private final CropRepository cropRepository;
    // 모든 작물 조회
    public List<CropDto> findCrops() {
        return cropRepository.findAll().stream().map(CropDto::new).collect(Collectors.toList());
    }

    // 단일 작물 조회
    public CropDto findById(Long cropId){
        return new CropDto(cropRepository.findById(cropId).get());
    }
}
