package therapia.farm.repository.farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
