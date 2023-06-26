package therapia.farm.repository.farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findMemberByNickname(String nickname);
    Optional<Member> findMemberById(Long id);
}
