package therapia.farm.repository.farm;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Farm;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    Farm findByName(String name);
    List<Farm> findAllByName(String name);
}

//package therapia.farm.repository;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Required;
//import org.springframework.stereotype.Repository;
//import therapia.farm.domain.Farm.Farm;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class FarmRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public void save(Farm farm){
//        em.persist(farm);
//    }
//
//    public Farm findOne(Long id){
//        return em.find(Farm.class, id);
//    }
//
//    public List<Farm> findAll(){
//        return em.createQuery("select f from Farm f", Farm .class)
//                .getResultList();
//    }
//
//    public List<Farm> findByName(String name){
//        return em.createQuery("select f from Farm f where f.name = :name", Farm.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
//}
//
//
