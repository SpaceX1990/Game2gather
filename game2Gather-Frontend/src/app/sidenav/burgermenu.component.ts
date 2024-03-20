import {Component} from '@angular/core';
import {SidebarModule} from "primeng/sidebar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'app-burgermenu',
  standalone: true,
  templateUrl: './burgermenu.component.html',
  imports: [
    SidebarModule,
    MatButtonModule,
    MatIconModule
  ],
  styleUrl: './burgermenu.component.scss'
})
export class BurgermenuComponent {
  isSidebarVisible: boolean = false;

}
