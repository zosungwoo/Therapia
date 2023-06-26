package therapia.farm.domain.crop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;
    private String name;
    private String ingredient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "crop_id")
    private Crop crop;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeStep> recipeSteps = new ArrayList<>();
}
