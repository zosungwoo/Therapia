package therapia.farm.domain.farm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();

    private String nickname;

    private String email;
}
