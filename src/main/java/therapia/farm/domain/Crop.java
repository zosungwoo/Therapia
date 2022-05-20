package therapia.farm.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Crop {
    @Id
    @GeneratedValue
    @Column(name = "crop_id")
    private Long id;

    private String name;

    private String season;

    private String temperature;

    private String storage;
}
