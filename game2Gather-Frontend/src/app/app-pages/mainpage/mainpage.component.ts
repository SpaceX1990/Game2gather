import {Component} from '@angular/core';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrl: './mainpage.component.scss'
})
export class MainpageComponent {

  loggedIn: boolean = false;

  logIN() {
    this.loggedIn = true;
  }
}
