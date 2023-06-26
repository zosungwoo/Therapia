package therapia.farm.dto.farm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import therapia.farm.domain.farm.Member;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String nickname;
    private String email;

    @Builder
    public MemberRequestDto(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public static Member toEntity(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .nickname(memberRequestDto.getNickname())
                .email(memberRequestDto.getEmail())
                .build();
    }
}
