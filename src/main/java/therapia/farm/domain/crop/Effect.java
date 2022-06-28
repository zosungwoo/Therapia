package therapia.farm.domain.crop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Effect {
    @Id
    @GeneratedValue
    @Column(name = "effect_id")
    private Long id;

    private String symptom;

    private String effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "crop_id")
    private Crop crop;
}
