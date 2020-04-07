import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Recipe } from 'src/app/shared/models/recipe';
import { ApiRecipeService } from 'src/app/shared/api-recipe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-recipe',
  templateUrl: './new-recipe.component.html',
  styleUrls: ['./new-recipe.component.scss']
})
export class NewRecipeComponent implements OnInit {

 

  newRecipe: FormGroup = new FormGroup({
    name: new FormControl(),
    description: new FormControl(),
    image: new FormControl(),
  });

  constructor(
    private fb: FormBuilder,
    private apiService: ApiRecipeService,
    private router: Router,
    ) {}


  ngOnInit(): void {
    // this.newRecipe = this.fb.group({
    //   name: this.fb.control('', Validators.required),
    //   desc: this.fb.control(''),
    //   image: this.fb.control(''),
    // });

  }


  onSave(){
    const val = this.newRecipe.getRawValue() as Recipe;
    this.apiService.postRecipe(val).subscribe(
      response => {
        this.router.navigate(['/']);
      },
      error => {
        alert('An error has occured while sending feedback');
      }
    )
    console.log(val);
  }

}
