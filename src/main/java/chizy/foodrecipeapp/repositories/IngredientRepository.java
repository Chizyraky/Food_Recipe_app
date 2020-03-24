package chizy.foodrecipeapp.repositories;

import chizy.foodrecipeapp.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

//        @Query("select i.id, i.name, i.amount\n" +
//            "from Recipe as r\n" +
//            "left join Recipe_ingredients as ri on (r.id = ri.recipe_id)\n" +
//            " left join Ingredient as i on (ri.ingredient_id = i.id)\n" +
//            "where r.id = :recipeId")
//        List<Ingredient> getIngredientsByRecipesId(@Param("recipeId")int recipeId);

}
