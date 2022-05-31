package therapia.farm.domain.crop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "crop_effect")
@Getter @Setter
public class CropEffect {
    @Id
    @GeneratedValue
    @Column(name = "crop_effect_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "effect_id")
    private Effect effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

}
