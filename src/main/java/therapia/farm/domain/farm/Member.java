package therapia.farm.domain.farm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String nickname;
    private String email;

    @Builder
    public Member(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
