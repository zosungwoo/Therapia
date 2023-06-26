package therapia.farm.dto.farm;

import lombok.Getter;

@Getter
public class MemberUpdateDto {
    private final Long id;
    private final String nickname;

    public MemberUpdateDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
