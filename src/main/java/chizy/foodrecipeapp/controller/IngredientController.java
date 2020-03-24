package chizy.foodrecipeapp.controller;

import chizy.foodrecipeapp.repositories.IngredientRepository;
import chizy.foodrecipeapp.services.IngredientService;
import chizy.foodrecipeapp.view.IngredientViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientRepository ingredientRepository;


    @GetMapping("/all")
    public List<IngredientViewModel> all() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/byId/{id}")
    public IngredientViewModel byId(@PathVariable("id") Integer id) {

        return ingredientService.getIngredientById(id);
    }

    @RequestMapping(value = "/byRecipe/{recipeId}", method = RequestMethod.GET)
    public List<IngredientViewModel> byNotebook(@PathVariable("recipeId") Integer recipeId) {

        return ingredientService.byRecipeId(recipeId);
    }

    @PostMapping
    public IngredientViewModel save(@RequestBody IngredientViewModel IngredientViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        if(IngredientViewModel.getId() != 0){
            return ingredientService.updateIngredient(IngredientViewModel) ;
        }

        return ingredientService.save(IngredientViewModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.ingredientRepository.deleteById(id);
    }
}