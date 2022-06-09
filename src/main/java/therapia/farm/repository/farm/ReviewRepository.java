package therapia.farm.repository.farm;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.Member;
import therapia.farm.domain.farm.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByfarmId(Long farmId);
    Review findReviewById(Long id);
}