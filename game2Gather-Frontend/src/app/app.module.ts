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
import {MatButton, MatButtonModule, MatFabButton} from "@angular/material/button";
import {ToolbarModule} from "primeng/toolbar";
import {InputTextModule} from "primeng/inputtext";
import {ChipsModule} from "primeng/chips";
import {InputNumberModule} from "primeng/inputnumber";
import {MatCardModule} from "@angular/material/card";
import {NgOptimizedImage} from "@angular/common";
import {ChipModule} from "primeng/chip";
import { CreateGameComponent } from './create-game/create-game.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";

@NgModule({
  declarations: [
    AppComponent,
    SessionlistComponent,
    MainpageComponent,
    GameCollectionComponent,
    CreateGameComponent,
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
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
