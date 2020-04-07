import { Component, OnInit } from '@angular/core';
import { ApiRecipeService } from '../shared/api-recipe.service';
import { Recipe } from '../shared/models/recipe';
import { Ingredient } from '../shared/models/ingredient';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.scss']
})
export class RecipeComponent implements OnInit {

  constructor(private apiService: ApiRecipeService) { }

  // recipes: Recipe[];

  recipe: Recipe;
  detailDeleted: Recipe;
  recipeIngredients: Ingredient[] = []

  isDisabled = false;

  ngOnInit(): void {
    // this.getAllRecipeFromService();
  }

  updateRecipeDetail(rec: Recipe) {
    this.recipe = rec;
  }

  getRecipeIngredients(recipeId: number): void {
    this.apiService.getIngredientsByRecipe(recipeId).subscribe(response => {
      this.recipeIngredients = response;
    },
      error => {
        alert('An error has occured while getting Ingrdients');
      })
  }

  showIngredients(show: boolean) {
    if (show) {
      this.getRecipeIngredients(this.recipe.id);
      this.isDisabled = true;

    } else {
      this.isDisabled = false;
    }
  }

  getRecipe( $event ){
    this.apiService.getRecipeById($event).subscribe(
      response => {
          this.recipe = response;
      },
      error => {
        alert('An error has occured while getting Ingrdients');
      }
    )
    console.log($event);
    console.log(this.detailDeleted);
  }

}
