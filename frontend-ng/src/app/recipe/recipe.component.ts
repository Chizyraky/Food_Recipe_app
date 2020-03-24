import { Component, OnInit } from '@angular/core';
import { ApiRecipeService } from '../shared/api-recipe.service';
import { Recipe } from '../shared/models/recipe';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.scss']
})
export class RecipeComponent implements OnInit {

  constructor(private apiService: ApiRecipeService) { }

  recipes: Recipe[];

  ngOnInit(): void {
    this.getAllRecipeFromService();
  }

  getAllRecipeFromService() {
    this.apiService.getAllRecipes().subscribe(
      response => {
        this.recipes = response;
        console.log(response);
      },
      error => {
        alert('An error has occured while getting recipes');
        alert(error.error.message);
        console.log(error);
      }
    )
  }

}
