package therapia.farm.domain.crop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class RecipeStep {
    @Id
    @GeneratedValue
    @Column(name = "recipe_step_id")
    private Long id;
    private Long stepIdx;
    private String cooking;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
