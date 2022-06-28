package therapia.farm.domain.crop;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<Recipe> recipes = new ArrayList<>();

}
