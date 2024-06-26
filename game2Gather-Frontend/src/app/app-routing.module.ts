import {Route, RouterModule, Routes} from '@angular/router';
import {MainpageComponent} from "./app-pages/mainpage/mainpage.component";
import {NgModule} from "@angular/core";
import {GameDetailsComponent} from "./app-pages/game-details/game-details.component";
import {GameCollectionComponent} from "./app-pages/game-collection/game-collection.component";
import {SessionlistComponent} from "./app-pages/sessionlist/sessionlist.component";
import {GameCreateComponent} from "./app-pages/game-create-or-update/game-create/game-create.component";
import {GameUpdateComponent} from "./app-pages/game-create-or-update/game-update/game-update.component";
import {SessionAddComponent} from "./app-pages/session-add-or-edit/session-add/session-add.component";
import {SessionEditComponent} from "./app-pages/session-add-or-edit/session-edit/session-edit.component";
import {SessionDetailsComponent} from "./app-pages/session-details/session-details.component";

const mainpageRoute: Route = {path: "mainpage", component: MainpageComponent}
const spielesammlungRoute: Route = {path: "spielesammlung", component: GameCollectionComponent}
const sessionliste: Route = {path: "sessionliste", component: SessionlistComponent}
const sessionDetails: Route = {path: "sessiondetails/:session-id", component: SessionDetailsComponent}
const gameDetails: Route = {path: "gamedetails/:game-id", component: GameDetailsComponent}
const createGameRoute: Route = {path: "creategame", component: GameCreateComponent}
const updateGameRoute: Route = {path: "editgame/:id", component: GameUpdateComponent}
const createSessionRoute: Route = {path:"createsession", component: SessionAddComponent}
const updateSessionRoute: Route = {path:"sessionliste/editsession/:id", component: SessionEditComponent}
const invalidRoute: Route = {path: "**", redirectTo: "/mainpage"}

//urls that are reachable under "{host}/{url}", where url is the path of each route (i.e. "{host]/mainpage")
//the routes bind to a specific a component that will be reachable and accessible on that url
const routes: Routes = [mainpageRoute, spielesammlungRoute, sessionliste, createGameRoute,gameDetails, updateGameRoute, createSessionRoute, updateSessionRoute, sessionDetails ];

@NgModule({
  imports: [RouterModule.forRoot([...routes, invalidRoute])],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
