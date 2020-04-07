import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { Recipe } from 'src/app/shared/models/recipe';

@Component({
  selector: 'app-recipe-item',
  templateUrl: './recipe-item.component.html',
  styleUrls: ['./recipe-item.component.scss']
})
export class RecipeItemComponent implements OnInit {

  @Input() recipe: Recipe;    // Recieve recipe from recipe-list.html
  @Output() recipeSelected = new EventEmitter<Recipe>();  // Sending the selected recipe to recipe-list.ts
  constructor() { }

  ngOnInit(): void {
  }

  onSelected() {
    console.log("PLEASE HELP1");
    this.recipeSelected.emit(this.recipe);
  }

}
