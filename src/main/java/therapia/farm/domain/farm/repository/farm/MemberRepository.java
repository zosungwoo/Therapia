package therapia.farm.domain.farm.repository.farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.farm.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByEmail(String email);

    Member findMemberByNickname(String nickname);
    Member findMemberById(Long id);
}