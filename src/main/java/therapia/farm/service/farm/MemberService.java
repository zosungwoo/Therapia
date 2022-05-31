package therapia.farm.service.farm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.farm.Member;
import therapia.farm.repository.farm.MemberRepository;

import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 유저 생성
    public Long createMember(Member member){
        memberRepository.save(member);
        return member.getId();
    }

    // 유저 정보 업데이트
    public void updateMember(Long memberId, String nickname, String email){
        Member member = memberRepository.findById(memberId).get();
        member.setNickname(nickname);
        member.setEmail(email);
    }

    // 유저 삭제
    public void removeMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    // 유저 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
