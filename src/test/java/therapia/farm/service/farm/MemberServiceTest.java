package therapia.farm.service.farm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import therapia.farm.domain.farm.Member;
import therapia.farm.repository.farm.MemberRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        String nickname = "정지연";
        String email = "wjdwldus2912@gmail.com";

        //when
        Long saveId = memberService.createMember(nickname, email);

        //then
        assertEquals(member, memberRepository.getById(saveId));
    }

    @Test
    @Rollback(false)
    public void 닉네임_변경() throws Exception {
        //given
        Member member = new Member();
        String nickname = "정지연";
        String email = "jung_j_yeon@naver.com";
        Long Id = memberService.createMember(nickname, email);

        //when
        String newNickname = "조성우";
        memberService.updateMember(Id, newNickname);

        //then
        assertEquals(newNickname, member.getNickname());
    }

    @Test
    @Rollback(false)
    public void 유저삭제() throws Exception {
        //given
        String nickname = "정지연";
        String email = "wjdwldus2912@gmail.com";
        Long Id = memberService.createMember(nickname, email);

        //when
        memberService.removeMember(Id);
        List<Member> result = memberService.findMembers();

        //then
        assertEquals(result.size(),0);
    }

    @Test
    @Rollback(false)
    public void 회원전체_조회() throws Exception {
        //given
        String nickname1 = "정지연";
        String email1 = "wjdwldus2912@gmail.com";
        Long Id1 = memberService.createMember(nickname1, email1);

        String nickname2 = "조성우";
        String email2 = "chosungwoo@gmail.com";
        Long Id2 = memberService.createMember(nickname2, email2);

        //when
        List<Member> result = memberService.findMembers();

        //then
        assertEquals(result.size(), 2);
    }

    @Test
    @Rollback(false)
    public void 회원단건_조회() throws Exception {
        //given
        String nickname1 = "정지연";
        String email1 = "wjdwldus2912@gmail.com";
        Long Id1 = memberService.createMember(nickname1, email1);

        String nickname2 = "조성우";
        String email2 = "chosungwoo@gmail.com";
        Long Id2 = memberService.createMember(nickname2, email2);

        //when
        Member findMember = memberService.findMemberByEmail(email1);

        //then
        assertEquals(findMember.getEmail(), email1);
    }

}