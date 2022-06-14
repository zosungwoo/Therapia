package therapia.farm.service.farm;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.repository.farm.FarmRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static therapia.farm.domain.farm.FarmCategory.EXP;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FarmServiceTest {

    @Autowired
    FarmService farmService;

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 농장_등록() throws Exception {
        //given
        Farm farm  = new Farm();
        farm.setAddress("충청남도 예산군 오가면 오촌리 318");
        farm.setCategory(EXP);
        farm.setLocation_x(126.8002577);
        farm.setLocation_y(36.7107201);
        farm.setPhone("041-334-5447");
        farm.setName("가나안농장");
        farm.setPlaceUrl("http://www.cnafarm.com");

        //when
        Long Id = farmService.createFarm(farm);

        //then
        assertEquals(farm, farmRepository.getById(Id));
    }

    @Test
    public void 농장_중복_예외() throws Exception {
        //given
        String name = "가나안농장";

        Farm farmA = new Farm();
        farmA.setName(name);

        Farm farmB = new Farm();
        farmB.setName(name);

        //when
        farmService.createFarm(farmA);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> farmService.createFarm(farmB));
        assertEquals("이미 존재하는 농장입니다.", thrown.getMessage());
    }

    @Test
    @Rollback(false)
    public void 농장삭제() throws Exception {
        //given
        Farm farm  = new Farm();
        farm.setAddress("충청남도 예산군 오가면 오촌리 318");
        farm.setCategory(EXP);
        farm.setLocation_x(126.8002577);
        farm.setLocation_y(36.7107201);
        farm.setPhone("041-334-5447");
        farm.setName("가나안농장");
        farm.setPlaceUrl("http://www.cnafarm.com");
        Long Id = farmService.createFarm(farm);

        //when
        farmService.removeFarm(Id);
        List<Farm> result = farmService.findFarms();

        //then
        assertEquals(result.size(),0);
    }


    @Test
    public void 농장조회() throws Exception {
        //given
        Farm farm = farmService.findById(1L);

        //when

        //then
        assertEquals(farm.getId(), 1L);
    }
}