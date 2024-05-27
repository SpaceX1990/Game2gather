import {Component, Injector} from '@angular/core';
import {SessionAddOrEditDirective} from "../session-add-or-edit.directive";
import {SessionModel} from "../../../models/session.model";
import {GameModel} from "../../../models/game.model";
import {Validators} from "@angular/forms";

@Component({
  selector: 'app-session-add',
  templateUrl: '../session-add-or-edit.component.html',
  styleUrls: ['../session-add-or-edit.component.scss']
})
export class SessionAddComponent extends SessionAddOrEditDirective {

  override htmlTemplateName = "Neue Session anlegen";
  override submitLabel = "Session erstellen";

  constructor(private newInjector: Injector) {
    super(newInjector);
    this.createSessionForm();
  }

  createSessionForm() {
    this.sessionForm = this.formBuilder.group({
      id: [''],
      sessionTitle: ['', Validators.required],
      active: true,
      maxPlayer: ['', Validators.required],
      userId: null,
      gameVotes: [[]],
      foodVotes: [[]],
      dateVotes: [[]],
    })
  }

  override onFormSubmit() {
    if (this.sessionForm.valid) {
      let newSession: SessionModel = this.sessionForm.value;
      const games = this.sessionForm.get("gameVotes")?.value || [];
      const foods = this.sessionForm.get("foodVotes")?.value || [];
      const dates = this.sessionForm.get("dateVotes")?.value || [];
      newSession.gameVotes = games.map((game: GameModel) => ({
        voteoption: game
      }));
      newSession.foodVotes = foods.map((food: string) => ({
        voteoption: food
      }))
      newSession.dateVotes = dates.map((date: Date) => ({
        voteoption: date
      }))
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
