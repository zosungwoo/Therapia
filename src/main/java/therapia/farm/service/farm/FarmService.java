package therapia.farm.service.farm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.FarmCategory;
import therapia.farm.domain.farm.Review;
import therapia.farm.repository.farm.FarmRepository;

import java.util.List;

@Service
@Transactional
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    // 농장 등록
    public Long createFarm(Farm farm){
        validateDuplicateMember(farm);
        farm.setReviewRating(0.0);  // 새로운 농장 Rating 0.0으로 설정
        farmRepository.save(farm);
        return farm.getId();
    }

    // 농장 업데이트 (변경 감지 이용)
    public void updateFarm(Long farmId, String name, FarmCategory category,
                           String phone, String address, String placeUrl, Double location_x, Double location_y){
        Farm tmp = new Farm();  // 중복 검사
        tmp.setName(name);
        validateDuplicateMember(tmp);

        Farm farm = farmRepository.findById(farmId).get();
        farm.setName(name);
        farm.setCategory(category);
        farm.setPhone(phone);
        farm.setAddress(address);
        farm.setPlaceUrl(placeUrl);
        farm.setLocation_x(location_x);
        farm.setLocation_y(location_y);
    }

    private void validateDuplicateMember(Farm farm){
        List<Farm> findFarms = farmRepository.findAllByName(farm.getName());
        if (!findFarms.isEmpty()){
            throw new IllegalStateException("이미 존재하는 농장입니다.");
        }
    }

    public void removeFarm(Long farmId){
        farmRepository.deleteById(farmId);
    }

    // 모든 농장 조회
    public List<Farm> findFarms(){
        return farmRepository.findAll();
    }

    // 단건 조회
    public Farm findOne(Long farmId){
        return farmRepository.findById(farmId).get();
    }

}
