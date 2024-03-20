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
import {MatFabButton} from "@angular/material/button";

@NgModule({
  declarations: [
    AppComponent,
    SessionlistComponent,
    MainpageComponent,
    GameCollectionComponent,
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
    MatFabButton
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
