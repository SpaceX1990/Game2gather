import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from "./app.component";
import {BrowserModule} from "@angular/platform-browser";
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SessionlistComponent} from "./sessionlist/sessionlist.component";
import {MainpageComponent} from "./mainpage/mainpage.component";
import {GameCollectionComponent} from "./game-collection/game-collection.component";
import {MatSidenavModule} from "@angular/material/sidenav";
import {SidebarModule} from "primeng/sidebar";
import {BurgermenuComponent} from './sidenav/burgermenu.component';
import {ButtonModule} from "primeng/button";
import {MatButton, MatFabButton} from "@angular/material/button";
import {ToolbarModule} from "primeng/toolbar";
import {InputTextModule} from "primeng/inputtext";
import {ChipsModule} from "primeng/chips";
import {InputNumberModule} from "primeng/inputnumber";
import {MatCardModule} from "@angular/material/card";
import {NgOptimizedImage} from "@angular/common";
import {ChipModule} from "primeng/chip";
import { GameFormComponent } from './game-form/game-form.component';
import { GameDetailsComponent } from './game-details/game-details.component';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    SessionlistComponent,
    MainpageComponent,
    GameCollectionComponent,
    GameFormComponent,
    GameDetailsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterOutlet,
    AppRoutingModule,
    MatSidenavModule,
    SidebarModule,
    BurgermenuComponent,
    ButtonModule,
    MatFabButton,
    MatButton,
    ToolbarModule,
    InputTextModule,
    ChipsModule,
    InputNumberModule,
    MatCardModule,
    NgOptimizedImage,
    ChipModule,
    MatLabel,
    MatFormField,
    ReactiveFormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
