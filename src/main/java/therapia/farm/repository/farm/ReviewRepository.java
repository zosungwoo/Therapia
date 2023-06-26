package therapia.farm.repository.farm;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findReviewByFarmId(Long farmId);
    Review findReviewById(Long id);
    List<Review> findAllByFarmId(Long farmId);
}