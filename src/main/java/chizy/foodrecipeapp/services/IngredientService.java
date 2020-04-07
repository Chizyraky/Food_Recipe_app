package chizy.foodrecipeapp.services;

import chizy.foodrecipeapp.Mapper;
import chizy.foodrecipeapp.models.Ingredient;
import chizy.foodrecipeapp.models.Recipe;
import chizy.foodrecipeapp.repositories.IngredientRepository;
import chizy.foodrecipeapp.repositories.RecipeRepository;
import chizy.foodrecipeapp.view.IngredientViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    public IngredientViewModel save(IngredientViewModel ingrCreateViewModel) {

        Ingredient ingredientEntity = mapper.convertToIngredientEntity(ingrCreateViewModel);
        // save note instance to db
        ingredientRepository.save(ingredientEntity);
        return mapper.convertToIngredientViewModel(ingredientEntity);
    }

    public IngredientViewModel getIngredientById(Integer id) {

        Optional<Ingredient> noteEntity = ingredientRepository.findById(id);
        if(noteEntity.isPresent()) {

            return mapper.convertToIngredientViewModel(noteEntity.get());
        }
        return IngredientViewModel.builder().build();
    }

    public List<IngredientViewModel> byRecipeId(Integer recipeId) {

        List<Ingredient> ingredients = new ArrayList<>();

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (recipe.isPresent()) {
            ingredients = recipe.get().getIngredients();
//            ingredients = ingredientRepository.getIngredientsByRecipesId(recipeId);
        }

        // map to note view model
        return ingredients.stream().map(ingredient -> mapper.convertToIngredientViewModel(ingredient)).collect(Collectors.toList());
    }

    public List<IngredientViewModel> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();

        // map from entity to view model
        return ingredients.stream()
                .map(ingredient -> this.mapper.convertToIngredientViewModel(ingredient))
                .collect(Collectors.toList());
    }


    public IngredientViewModel updateIngredient(IngredientViewModel noteViewModel) {
        Ingredient entity = mapper.convertToIngredientEntity(noteViewModel);

        ingredientRepository.save(entity);

        return mapper.convertToIngredientViewModel(entity);
    }
}
