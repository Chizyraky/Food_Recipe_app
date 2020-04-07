package chizy.foodrecipeapp.controller;

import chizy.foodrecipeapp.repositories.RecipeRepository;
import chizy.foodrecipeapp.services.RecipeService;
import chizy.foodrecipeapp.view.RecipeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@CrossOrigin
public class RecipeController {
// http://localhost:8085/api/recipe
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;



    @GetMapping("/all")
    public List<RecipeViewModel> all() {
        return recipeService.getAllRecipe();
    }

    @PostMapping
    public RecipeViewModel save(@RequestBody RecipeViewModel notebookViewModel, BindingResult bindingResult) {

        //
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }

//        if(notebookViewModel.getId() != 0){
//            return recipeService.updateNotebook(notebookViewModel);
//        }
        return recipeService.save(notebookViewModel);
    }

    @GetMapping("/{id}")
    public RecipeViewModel getById(@PathVariable Integer id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { recipeRepository.deleteById(id);}
}
