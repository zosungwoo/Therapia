package therapia.farm.domain.crop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Effect {
    @Id
    @GeneratedValue
    @Column(name = "effect_id")
    private Long id;
    private String symptom;
    private String effect;
    @JsonIgnore
    @OneToMany(mappedBy = "effect", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CropEffect> cropEffectList = new ArrayList<>();
}
