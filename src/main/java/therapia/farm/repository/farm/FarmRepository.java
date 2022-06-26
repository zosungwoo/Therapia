package therapia.farm.repository.farm;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.FarmCategory;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    Farm findByName(String name);
    List<Farm> findAllByName(String name);

    List<Farm> findAllByCategory(FarmCategory farmCategory);
}
