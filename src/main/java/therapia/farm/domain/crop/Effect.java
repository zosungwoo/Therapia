package therapia.farm.domain.crop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Effect {
    @Id
    @GeneratedValue
    @Column(name = "effect_id")
    private Long id;

    private String symptom;

    private String effect;
}
