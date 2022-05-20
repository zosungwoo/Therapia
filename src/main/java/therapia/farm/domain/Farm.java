package therapia.farm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Farm {
    @Id @GeneratedValue
    @Column(name = "farm_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FarmCategory category;

    private String name;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private String phone;

    private String address;

    private String placeUrl; // 이거 뭐였더라

    private Double location_x;

    private Double location_y;
}
