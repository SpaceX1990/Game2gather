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
import {MatButton, MatButtonModule, MatFabButton} from "@angular/material/button";
import {ToolbarModule} from "primeng/toolbar";
import {InputTextModule} from "primeng/inputtext";
import {ChipsModule} from "primeng/chips";
import {InputNumberModule} from "primeng/inputnumber";
import {MatCardModule} from "@angular/material/card";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {ChipModule} from "primeng/chip";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ConfirmationService} from "primeng/api";
import {CarouselModule} from "primeng/carousel";
import {ImageModule} from "primeng/image";
import { GameCreateComponent } from './app-pages/game-create-or-update/game-create/game-create.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {GameUpdateComponent} from "./app-pages/game-create-or-update/game-update/game-update.component";
import {SessionAddComponent} from "./app-pages/session-add-or-edit/session-add/session-add.component";
import {MultiSelectModule} from "primeng/multiselect";
import {CalendarModule} from "primeng/calendar";
import {SessionEditComponent} from "./app-pages/session-add-or-edit/session-edit/session-edit.component";

@NgModule({
  declarations: [
    AppComponent,
    SessionlistComponent,
    MainpageComponent,
    GameCollectionComponent,
    GameUpdateComponent,
    GameCreateComponent,
    SessionAddComponent,
    SessionEditComponent
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
    ConfirmDialogModule,
    ChipModule,
    CarouselModule,
    ImageModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MultiSelectModule,
    FormsModule,
    CalendarModule,
    CommonModule,
    ],
  providers:[ConfirmationService],
  bootstrap: [AppComponent],
  exports: [SessionAddComponent]
})
export class AppModule {
}
