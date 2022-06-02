package therapia.farm.dto.farm;

import lombok.AllArgsConstructor;
import lombok.Data;
import therapia.farm.domain.farm.Member;

@Data
@AllArgsConstructor
public class MemberDto {
    private String nickname;
    private String email;

    public static MemberDto of (Member m) {
        return new MemberDto(
                m.getNickname(),
                m.getEmail()
        );
    }
}
