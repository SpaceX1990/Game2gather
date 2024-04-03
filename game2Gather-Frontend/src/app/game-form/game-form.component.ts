import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {GameModel} from "../models/game.model";

@Component({
  selector: 'app-game-form',
  templateUrl: './game-form.component.html',
  styleUrl: './game-form.component.scss'
})
export class GameFormComponent {
  gameForm: FormGroup;
  // gameId: number;
  // gameModel: GameModel;

  constructor(
    protected fb: FormBuilder
  ) {
    this.gameForm = this.fb.group({
      // id: [{value: '', disabled: true}],
      // lastName: ['', Validators.required],
      // firstName: ['', Validators.required],
      // street: ['', Validators.required],
      // postcode: ['', [Validators.required, Validators.pattern(/^\d{5}$/)]],
      // city: ['', Validators.required],
      // phone: ['', [Validators.pattern(/^(\+?\d+[\s\-]*)+$/)]],
    });
  }

  ngOnInit() {
    // let employeeIdParam = this.route.snapshot.paramMap.get(':employeeId');
    //
    // if (employeeIdParam != null) {
    //   this.employeeId = Number.parseInt(employeeIdParam);
    // }
    //
    // if (this.employeeId != null) {
    //   this.employeeService.getById(this.employeeId).subscribe(data => {
    //     this.employeeModel = data;
    //     this.fillFormWithValues();
    //   });
    // }
  }

  fillFormWithValues() {
    this.gameForm.patchValue({
      // id: this.employeeModel.id,
      // lastName: this.employeeModel.lastName,
      // firstName: this.employeeModel.firstName,
      // street: this.employeeModel.street,
      // postcode: this.employeeModel.postcode,
      // city: this.employeeModel.city,
      // phone: this.employeeModel.phone,
    });
  }
}
