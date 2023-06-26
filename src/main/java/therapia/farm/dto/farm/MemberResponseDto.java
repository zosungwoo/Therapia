package therapia.farm.dto.farm;

import lombok.Getter;
import therapia.farm.domain.farm.Member;

@Getter
public class MemberResponseDto {
    private final Long id;
    private final String nickname;
    private final String email;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}
