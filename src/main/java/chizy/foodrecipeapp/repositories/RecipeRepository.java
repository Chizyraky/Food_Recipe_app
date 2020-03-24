package chizy.foodrecipeapp.repositories;

import chizy.foodrecipeapp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>  {
    Optional<Recipe> findById(Integer id);
}
