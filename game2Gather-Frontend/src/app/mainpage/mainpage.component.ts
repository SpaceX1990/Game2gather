import { Component } from '@angular/core';
import {ButtonModule} from "primeng/button";
import {MatButton} from "@angular/material/button";
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-mainpage',
  standalone: true,
  imports: [
    ButtonModule,
    MatButton,
    NgIf,
    RouterLink
  ],
  templateUrl: './mainpage.component.html',
  styleUrl: './mainpage.component.scss'
})
export class MainpageComponent {

  loggedIn: boolean = false;

  logIN() {
    this.loggedIn = true;
  }
}
