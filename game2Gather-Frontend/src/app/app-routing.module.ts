import {Route, RouterModule, Routes} from '@angular/router';
import {MainpageComponent} from "./app-pages/mainpage/mainpage.component";
import {NgModule} from "@angular/core";
import {GameCollectionComponent} from "./app-pages/game-collection/game-collection.component";
import {SessionlistComponent} from "./app-pages/sessionlist/sessionlist.component";
import {GameCreateComponent} from "./app-pages/game-create-or-update/game-create/game-create.component";
import {GameUpdateComponent} from "./app-pages/game-create-or-update/game-update/game-update.component";
import {SessionAddComponent} from "./app-pages/session-add-or-edit/session-add/session-add.component";

const mainpageRoute: Route = {path: "mainpage", component: MainpageComponent}
const spielesammlungRoute: Route = {path: "spielesammlung", component: GameCollectionComponent}
const sessionliste: Route = {path: "sessionliste", component: SessionlistComponent}
const createGameRoute: Route = {path: "creategame", component: GameCreateComponent}
const updateGameRoute: Route = {path: "editgame/:id", component: GameUpdateComponent}
const createSessionRoute: Route = {path:"createsession", component: SessionAddComponent}
const invalidRoute: Route = {path: "**", redirectTo: "/mainpage"}

const routes: Routes = [mainpageRoute, spielesammlungRoute, sessionliste, createGameRoute, updateGameRoute, createSessionRoute, invalidRoute];

@NgModule({
  imports: [RouterModule.forRoot([...routes, ])],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
