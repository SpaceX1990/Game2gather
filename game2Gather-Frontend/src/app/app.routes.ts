import {Route, RouterModule, Routes} from '@angular/router';
import {MainpageComponent} from "./mainpage/mainpage.component";
import {NgModule} from "@angular/core";

const mainboardRoute: Route = {path: "mainboard", component: MainpageComponent}
const emptyRoute: Route = {path: "", redirectTo: "mainboard", pathMatch: "full"}

export const routes: Routes = [mainboardRoute, emptyRoute];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutes {
}
