package chizy.foodrecipeapp.services;

import chizy.foodrecipeapp.Mapper;
import chizy.foodrecipeapp.models.Ingredient;
import chizy.foodrecipeapp.models.Recipe;
import chizy.foodrecipeapp.repositories.RecipeRepository;
import chizy.foodrecipeapp.view.IngredientViewModel;
import chizy.foodrecipeapp.view.RecipeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    public RecipeViewModel save(RecipeViewModel notebookViewModel){

        Recipe recipeEntity = mapper.convertToRecipeEntity(notebookViewModel);

        // save notebookEntity instance to db
        this.recipeRepository.save(recipeEntity);


        return mapper.convertToRecipeViewModel(recipeEntity);
    }

    public List<RecipeViewModel> getAllRecipe() {
        List<Recipe> allCategories = recipeRepository.findAll();

        return allCategories
                .stream()
                .map(mapper::convertToRecipeViewModel)
                .collect(Collectors.toList());
    }

    public RecipeViewModel updateRecipe(RecipeViewModel recipeViewModel) {

        Recipe entity = mapper.convertToRecipeEntity(recipeViewModel);

        recipeRepository.save(entity);

        return mapper.convertToRecipeViewModel(entity);
    }

    public RecipeViewModel getRecipeById(Integer id) {

        Optional<Recipe> recipeEntity = recipeRepository.findById(id);
        if(recipeEntity.isPresent()) {

            return mapper.convertToRecipeViewModel(recipeEntity.get());
        }
        return RecipeViewModel.builder().build();
    }

}
