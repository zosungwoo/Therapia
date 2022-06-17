package therapia.farm.domain.farm.repository.crop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.crop.Crop;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}
