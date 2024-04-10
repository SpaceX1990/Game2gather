import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from "./app.component";
import {BrowserModule} from "@angular/platform-browser";
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SessionlistComponent} from "./app-pages/sessionlist/sessionlist.component";
import {MainpageComponent} from "./app-pages/mainpage/mainpage.component";
import {GameCollectionComponent} from "./app-pages/game-collection/game-collection.component";
import {MatSidenavModule} from "@angular/material/sidenav";
import {SidebarModule} from "primeng/sidebar";
import {BurgermenuComponent} from './standalone-components/sidenav/burgermenu.component';
import {ButtonModule} from "primeng/button";
import {MatButton, MatFabButton} from "@angular/material/button";
import {ToolbarModule} from "primeng/toolbar";
import {InputTextModule} from "primeng/inputtext";
import {ChipsModule} from "primeng/chips";
import {InputNumberModule} from "primeng/inputnumber";
import {MatCardModule} from "@angular/material/card";
import {NgOptimizedImage} from "@angular/common";
import {ChipModule} from "primeng/chip";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ConfirmationService} from "primeng/api";
import {CarouselModule} from "primeng/carousel";
import {ImageModule} from "primeng/image";

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
    MatFabButton,
    MatButton,
    ToolbarModule,
    InputTextModule,
    ChipsModule,
    InputNumberModule,
    MatCardModule,
    NgOptimizedImage,
    ChipModule,
    ConfirmDialogModule,
    CarouselModule,
    ImageModule
  ],
  providers:[ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
