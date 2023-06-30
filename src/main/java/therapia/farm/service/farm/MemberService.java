package therapia.farm.service.farm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.farm.Member;
import therapia.farm.dto.farm.MemberRequestDto;
import therapia.farm.dto.farm.MemberResponseDto;
import therapia.farm.dto.farm.MemberUpdateDto;
import therapia.farm.exception.CustomException;
import therapia.farm.repository.farm.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 유저 생성
    @Transactional
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto){
        Optional<Member> member = memberRepository.findMemberByEmail(memberRequestDto.getEmail());
        if(member.isEmpty()){
            if (memberRepository.findMemberByNickname(memberRequestDto.getNickname()).isPresent()) {
                throw new CustomException("존재하는 닉네임");
            }
            Member newMember = memberRepository.save(MemberRequestDto.toEntity(memberRequestDto));
            return new MemberResponseDto(newMember);
        } else {
            return new MemberResponseDto(member.get());
        }
    }

    // 유저 정보 업데이트
    @Transactional
    public MemberResponseDto updateMember(MemberUpdateDto memberRequestDto){
        if (memberRepository.findMemberByNickname(memberRequestDto.getNickname()).isPresent()) {
            throw new CustomException("존재하는 닉네임");
        }
        Member member = memberRepository.getById(memberRequestDto.getId());
        member.updateNickname(memberRequestDto.getNickname());

        return new MemberResponseDto(member);
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
}
