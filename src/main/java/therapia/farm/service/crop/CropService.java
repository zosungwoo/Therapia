package therapia.farm.service.crop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.crop.Crop;
import therapia.farm.domain.farm.repository.crop.CropRepository;

import java.util.List;

@Service
@Transactional
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public Long createCrop(Crop crop){
        cropRepository.save(crop);
        return crop.getId();
    }

    public Crop findOne(Long cropId){
        return cropRepository.findById(cropId).get();
    }

    // 모든 작물 조회
    public List<Crop> findCrops(){
        return cropRepository.findAll();  // 페이징도 가능
    }


}
