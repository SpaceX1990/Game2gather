import {Component, Injector} from '@angular/core';
import {SessionAddOrEditDirective} from "../session-add-or-edit.directive";
import {SessionModel} from "../../../models/sessionModel";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-session-add',
  templateUrl: './session-add-or-edit.component.html',
  styleUrl: './session-add-or-edit.component.scss'
})
export class SessionAddComponent extends SessionAddOrEditDirective {

  override htmlTemplateName = "Neue Session anlegen";

  constructor(private formBuilder: FormBuilder, private newInjector: Injector) {
    super(newInjector);
    this.createSessionForm();
  }

  createSessionForm() {
    this.sessionForm = this.formBuilder.group({
      id: [''],
      sessionTitle: [''],
      active: true,
      maxPlayer: [''],
      userId: null,
      gameOptions: [''],
      newFood: [''],
      dateOption: [''],
    })
  }

  onFormSubmit() {
    if (this.sessionForm.valid) {
      const newSession: SessionModel = this.sessionForm.value;
      this.sessionService.addSession(newSession).subscribe({
        next: () => {
          this.isSubmit = true;
          this.router.navigate(['sessionliste']);
        },
      });
    }
  }

  addFood() {
    const newFoodValue = this.sessionForm.get('newFood')!.value;
    const currentFoodOptions = this.sessionForm.get('newFood')!.value || [];
    currentFoodOptions.push(newFoodValue);
    this.sessionForm.get('newFood')!.setValue(currentFoodOptions);
  }
}

