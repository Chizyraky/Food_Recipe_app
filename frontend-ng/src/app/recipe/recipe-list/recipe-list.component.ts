import { Component, OnInit, Output, Inject, EventEmitter, Input, SimpleChanges } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Recipe } from 'src/app/shared/models/recipe';
import { ApiRecipeService } from 'src/app/shared/api-recipe.service';
import { BehaviorSubject } from 'rxjs';
import { FormBuilder, FormGroup, NgForm, Validators, FormControl } from '@angular/forms';
import { FormGroupDirective } from '@angular/forms'

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.scss']
})
export class RecipeListComponent implements OnInit {

  @Output() recipeUpdate = new EventEmitter<Recipe>();
  @Input() deletedDetail: Recipe;
  recipes: Recipe[] = [];

  private messageSource = new BehaviorSubject<Recipe>(undefined);
  currentMessage = this.messageSource.asObservable();

  constructor(
    private apiService: ApiRecipeService,
     public dialog: MatDialog,
     
     ) { }

  // On Page initialization, It runs getAllRecipe()
  ngOnInit() {
    this.getAllRecipe();
  }

  // Detects If list has been changed
  ngOnChanges(changes: SimpleChanges) {

    if (typeof changes['deletedDetail'] !== "undefined") {
 
      // retrieve the change variable change info
      var change = changes['deletedDetail'];

      // only perform the task if the value has been changed
      if (!change.isFirstChange()) {
          // execute the Http request and retrieve the result
          const indexOfRecipe = this.recipes.indexOf(this.deletedDetail);
          this.recipes.splice(indexOfRecipe, 1);
          this.onRecipeSelect(undefined);
      }

    }
  }

  // Gets recipes from database using ApiService
  public getAllRecipe() {
    this.apiService.getAllRecipes().subscribe(
      response => {
        this.recipes = response;
      },
      error => {
        alert('An error has occured while retrieving Recipes');
      }
    );
  }

  // This sends out selected recipe gotten from recipe-item
  onRecipeSelect(recipe: Recipe) {
    // this.messageSource.next(recipe)
    this.recipeUpdate.emit(recipe);
    console.log("PLEASE HELP2");
  }




  // openDialog(): void {
  //   const dialogRef = this.dialog.open(DialogNewRecipe, {
  //     width: '250px',
  //     // data: {name: this.name, animal: this.animal}
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed');
  //     // this.animal = result;
  //   });
  // }

}
