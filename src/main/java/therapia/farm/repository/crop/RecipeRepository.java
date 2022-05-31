package therapia.farm.repository.crop;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import therapia.farm.domain.crop.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
