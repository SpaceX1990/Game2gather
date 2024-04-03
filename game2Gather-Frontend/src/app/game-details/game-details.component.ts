import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {GameFormComponent} from "../game-form/game-form.component";

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrl: './game-details.component.scss'
})
export class GameDetailsComponent extends GameFormComponent{
  // constructor(protected routerService: RouterService,
  //             protected employeeService: EmployeeService,
  //             protected notificationService: NotificationService,
  //             protected fb: FormBuilder,
  //             protected route: ActivatedRoute
  // ) {
  //   super(employeeService, notificationService, fb, route, routerService);
  // }
}
