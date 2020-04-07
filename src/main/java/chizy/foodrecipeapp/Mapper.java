package chizy.foodrecipeapp;

import chizy.foodrecipeapp.models.Ingredient;
import chizy.foodrecipeapp.models.Recipe;
import chizy.foodrecipeapp.repositories.RecipeRepository;
import chizy.foodrecipeapp.view.IngredientViewModel;
import chizy.foodrecipeapp.view.RecipeViewModel;
import lombok.var;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private RecipeRepository recipeRepository;

    // Alternative to @Autowired
    public Mapper(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public IngredientViewModel convertToIngredientViewModel(Ingredient entity) {
        var viewModel = new IngredientViewModel();

        viewModel.setId(entity.getId());
        viewModel.setName(entity.getName());
        viewModel.setAmount(entity.getAmount());

        return viewModel;
    }

    public Ingredient convertToIngredientEntity(IngredientViewModel viewModel) {
        Ingredient entity;

        if(viewModel.getId() != 0){
            // Editing / Replacing database model
            entity = new Ingredient( viewModel.getId(), viewModel.getName(), viewModel.getAmount());

        } else{
            entity = new Ingredient( viewModel.getName(), viewModel.getAmount());
        }
        return entity;
    }

    public RecipeViewModel convertToRecipeViewModel(Recipe entity) {

        var viewModel = new RecipeViewModel();
        viewModel.setId(entity.getId());
        viewModel.setName(entity.getName());
        viewModel.setDescription(entity.getDescription());
        viewModel.setImage(entity.getImage());

        return viewModel;
    }

    public Recipe convertToRecipeEntity(RecipeViewModel viewModel) {
        var note = this.recipeRepository.findById(viewModel.getId());
        Recipe entity;
        if(viewModel.getId() == 0 ) {
            entity = new Recipe(viewModel.getName(), viewModel.getDescription(), viewModel.getImage());
        } else {
            entity = new Recipe(viewModel.getId(), viewModel.getName(), viewModel.getDescription(), viewModel.getImage());
        }
        return entity;
    }

}
