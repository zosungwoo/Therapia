package therapia.farm.dto.crop;

import lombok.Getter;
import therapia.farm.domain.crop.Crop;
@Getter
public class CropDto {
    private final Long id;
    private final String name;
    private final String season;
    private final String temperature;
    private final String storage;
    private final String img;

    public CropDto(Crop crop) {
        this.id = crop.getId();
        this.name = crop.getName();
        this.season = crop.getSeason();
        this.temperature = crop.getTemperature();
        this.storage = crop.getStorage();
        this.img = crop.getImg();
    }
}
