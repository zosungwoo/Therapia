package therapia.farm.domain.farm.repository.crop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.crop.Effect;

@Repository
public interface EffectRepository extends JpaRepository<Effect, Long> {

}
