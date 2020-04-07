import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Output() optionSelected: EventEmitter<String> = new EventEmitter<String>();
  @Output() cookListToggle: EventEmitter<boolean> = new EventEmitter<boolean>();

  ckToggle = false;
  onIngre = false;
  onrecipe = true;
  onList = false;


  constructor() { }

  ngOnInit() {
  }

  onSelect(option: string) {
    if (option === "shopping-list") {
      this.onIngre = false;
      this.onList = true;
      this.onrecipe = false;
    } else if (option === "recipes") {
      this.onIngre = false;
      this.onList = false;
      this.onrecipe = true;
    } else {
      this.onIngre = true;
      this.onList = false;
      this.onrecipe = false;
    }
    this.optionSelected.emit(option);
  }

  cookListClick() {
    this.ckToggle = !this.ckToggle;
    this.cookListToggle.emit(this.ckToggle);
    console.log("Sent IT");
    console.log(this.ckToggle);
  }

  burgerCntrl() {
    document.querySelector('.navbar-burger').classList.toggle('is-active');
    document.querySelector('.navbar-menu').classList.toggle('is-active');
    console.log("Navbar clicked");
  }

}
