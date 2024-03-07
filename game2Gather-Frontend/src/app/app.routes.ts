import {Route, RouterModule, Routes} from '@angular/router';
import {MainboardComponent} from "./mainboard/mainboard/mainboard/mainboard.component";
import {NgModule} from "@angular/core";

const mainboardRoute: Route = {path: "mainboard", component: MainboardComponent}
const emptyRoute: Route = {path: "", redirectTo: "mainboard", pathMatch: "full"}

export const routes: Routes = [mainboardRoute, emptyRoute];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutes {
}
