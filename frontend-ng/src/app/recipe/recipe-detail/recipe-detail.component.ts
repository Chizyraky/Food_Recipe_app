import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Recipe } from 'src/app/shared/models/recipe';
import { ApiRecipeService } from 'src/app/shared/api-recipe.service';
import { Router } from '@angular/router';
import { RecipeListComponent } from '../recipe-list/recipe-list.component';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.scss']
})
export class RecipeDetailComponent implements OnInit {

  @Input() recipeDetail: Recipe;
  @Output() deletedDetail = new EventEmitter<Recipe>();
  ingredDetail = false;
  ingredArrowIcon = "";
  @Output() showDetailIngredient = new EventEmitter<boolean>();
  constructor(private apiService: ApiRecipeService,
     private router: Router) { }

  ngOnInit() {
    console.log(this.recipeDetail);
  }

  deleteRecipe(recipe: Recipe) {
    if (confirm('Are you sure you want to delete notebook?')) {
      this.apiService.deleteRecipe(recipe.id).subscribe(
        response => {
          this.deletedDetail.emit(recipe);
          // this.router.navigate(["/"]);

        },
        error => {
          alert('An error has occured while sending feedback');
        }
      );
    }
  }

  toggleIngredient() {
    this.ingredDetail = !this.ingredDetail;
    this.showDetailIngredient.emit(this.ingredDetail);
  }

}
