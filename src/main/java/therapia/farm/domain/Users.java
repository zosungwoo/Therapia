package therapia.farm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Users {
    @Id @GeneratedValue
    @Column(name = "users_id")
    private Long id;

//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();

    private String nickname;

    private String email;
}
