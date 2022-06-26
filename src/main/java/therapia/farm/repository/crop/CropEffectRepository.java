package therapia.farm.repository.crop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.crop.CropEffect;

import java.util.List;

@Repository
public interface CropEffectRepository extends JpaRepository<CropEffect, Long> {
    List<CropEffect> findAllByCropId(Long cropId);
    List<CropEffect> findAllByEffectId(Long effectId);
}
