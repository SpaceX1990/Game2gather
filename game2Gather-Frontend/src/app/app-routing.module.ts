import {Route, RouterModule, Routes} from '@angular/router';
import {MainpageComponent} from "./mainpage/mainpage.component";
import {NgModule} from "@angular/core";
import {GameCollectionComponent} from "./game-collection/game-collection.component";
import {SessionlistComponent} from "./sessionlist/sessionlist.component";
import {CreateGameComponent} from "./create-game/create-game.component";

const mainpageRoute: Route = {path: "mainpage", component: MainpageComponent}
const spielesammlungRoute: Route = {path: "spielesammlung", component: GameCollectionComponent}
const sessionliste: Route = {path: "sessionliste", component: SessionlistComponent}
const createGame: Route = {path: "creategame", component: CreateGameComponent}
const invalidRoute: Route = {path: "**", redirectTo: "/mainpage"}

export const routes: Routes = [mainpageRoute,spielesammlungRoute,sessionliste, createGame, invalidRoute];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
