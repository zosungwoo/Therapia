package therapia.farm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;

    private String photoUrl;

    private String ingredient;

    //private List<> cooking;  요리법...인데 아직 구조를 모르니까 보류
}
