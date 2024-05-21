import {Component, Injector} from '@angular/core';
import {SessionAddOrEditDirective} from "../session-add-or-edit.directive";
import {SessionModel} from "../../../models/session.model";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-session-add',
  templateUrl: '../session-add-or-edit.component.html',
  styleUrls: ['../session-add-or-edit.component.scss']
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
      gameVotes: [[]],
      foodVotes: [[]],
      dateVotes: [[]],
    })
  }

  override onFormSubmit() {
    if (this.sessionForm.valid) {
      const newSession: SessionModel = this.sessionForm.value;
      newSession.userId = 1;
      this.sessionService.saveSession(newSession).subscribe({
        next: () => {
          this.isSubmit = true;
          this.router.navigate(['sessionliste']);
        },
      });
    }
  }
}
