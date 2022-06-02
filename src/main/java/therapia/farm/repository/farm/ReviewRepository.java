package therapia.farm.repository.farm;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByfarmId(Long farmId);
}

//package therapia.farm.repository;
//
//
//import org.springframework.stereotype.Repository;
//import therapia.farm.domain.Farm.Farm;
//import therapia.farm.domain.Farm.Review;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class ReviewRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public void save(Review review){
//        em.persist(review);
//    }
//
//    public Review findOne(Long id) {
//        return em.find(Review.class, id);
//    }
//
//    public List<Review> findAll(){
//        return em.createQuery("select r from Review r", Review.class)
//                .getResultList();
//    }
//
//    public List<Review> findByFarm(Long id){
//        return em.createQuery("select r from Review r where r.farm_id = :id", Review.class)  // farm_id로 해야하는지, farm으로 해야하는지..
//                .setParameter("id", id)
//                .getResultList();
//    }
//}
