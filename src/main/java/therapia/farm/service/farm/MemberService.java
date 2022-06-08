package therapia.farm.service.farm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.farm.Member;
import therapia.farm.dto.farm.MemberDto;
import therapia.farm.repository.farm.MemberRepository;

import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 유저 생성
    @Transactional
    public Long createMember(String nickname, String email){
        Member newMember = new Member();
        Member member = findMemberByEmail(email);
        if(member == null ){
            newMember.setEmail(email);
            newMember.setNickname(nickname);
            memberRepository.save(newMember);
            return newMember.getId();
        } else {
            return member.getId();
        }

    }

    // 유저 정보 업데이트
    @Transactional
    public void updateMember(Long memberId, String nickname){
        Member member = memberRepository.findById(memberId).get();
        member.setNickname(nickname);
    }

    // 유저 삭제
    @Transactional
    public void removeMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    // 유저 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findMemberByEmail(String email){
        return memberRepository.findMemberByEmail(email);
    }

    public String findNicknameById(Long memberId) {
        return memberRepository.getById(memberId).getNickname();
    }

    public Member findMemberById(Long memberId){
        return memberRepository.getById(memberId);
    }
}
