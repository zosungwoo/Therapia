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
        member.setNickname("정지연");
        member.setEmail("wjdwldus2912@gmail.com");

        //when
        Long saveId = memberService.createMember(member);

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
        member.setNickname(nickname);
        member.setEmail(email);
        Long Id = memberService.createMember(member);

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
        Member member = new Member();
        member.setEmail("wjdwldus2912@gmail.com");
        member.setNickname("정지연");
        Long Id = memberService.createMember(member);

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
        Member member1 = new Member();
        member1.setEmail("wjdwldus2912@gmail.com");
        member1.setNickname("정지연");
        Long Id1 = memberService.createMember(member1);

        Member member2 = new Member();
        member2.setEmail("chosungwoo@gmail.com");
        member2.setNickname("조성우");
        Long Id2 = memberService.createMember(member2);

        //when
        List<Member> result = memberService.findMembers();

        //then
        assertEquals(result.size(), 2);
    }

    @Test
    @Rollback(false)
    public void 회원단건_조회() throws Exception {
        //given
        Member member1 = new Member();
        member1.setEmail("wjdwldus2912@gmail.com");
        member1.setNickname("정지연");
        Long Id1 = memberService.createMember(member1);

        Member member2 = new Member();
        member2.setEmail("chosungwoo@gmail.com");
        member2.setNickname("조성우");
        Long Id2 = memberService.createMember(member2);

        //when
        Member findMember = memberService.findOne(Id1);

        //then
        assertEquals(findMember, member1);
    }

}