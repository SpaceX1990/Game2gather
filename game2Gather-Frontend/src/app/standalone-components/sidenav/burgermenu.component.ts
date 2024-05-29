import {Component} from '@angular/core';
import {SidebarModule} from "primeng/sidebar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {ButtonModule} from "primeng/button";
import {RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";
import {MenuModule} from "primeng/menu";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-burgermenu',
  standalone: true,
  templateUrl: './burgermenu.component.html',
  imports: [
    SidebarModule,
    MatButtonModule,
    MatIconModule,
    ButtonModule,
    RouterLink,
    NgForOf,
    MenuModule
  ],
  styleUrl: './burgermenu.component.scss'
})
export class BurgermenuComponent {
  protected isSidebarVisible: boolean = false;

  protected navBarPageList: MenuItem[] = [{
    //options to display in sidenav with functions and page-routing
    items: [
      {routerLink: "/mainpage", label: "Startseite", command: () => this.toggleSidebar()},
      {routerLink: "/sessionliste", label: "Alle Sessions", command: () => this.toggleSidebar()},
      {routerLink: "/spielesammlung", label: "Alle Spiele", command: () => this.toggleSidebar()},
      {routerLink: "/", label: "Ausloggen", command: () => this.toggleSidebar()}
    ]
  }]

  protected toggleSidebar() {
    this.isSidebarVisible = !this.isSidebarVisible;
  }
}
