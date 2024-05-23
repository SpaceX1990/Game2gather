import {Component, Injector} from '@angular/core';
import {SessionAddOrEditDirective} from "../session-add-or-edit.directive";
import {SessionModel} from "../../../models/session.model";
import {GameModel} from "../../../models/game.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-session-add',
  templateUrl: '../session-add-or-edit.component.html',
  styleUrls: ['../session-add-or-edit.component.scss']
})
export class SessionEditComponent extends SessionAddOrEditDirective {

  override htmlTemplateName = "Session bearbeiten";

  constructor(
    private newInjector: Injector,
    private route: ActivatedRoute
  ) {
    super(newInjector);
    this.getSessionForm();
  }

  getSessionForm(): void {
    const id: string = this.route.snapshot.paramMap.get('id')!;
    this.sessionService.getSession(id).subscribe(session => {
      this.sessionForm = this.formBuilder.group({
        id: [session.id],
        sessionTitle: [session.sessionTitle],
        active: [session.active],
        maxPlayer: [session.maxPlayer],
        userId: [session.userId],
        gameVotes: [session.gameVotes],
        foodVotes: [session.foodVotes],
        dateVotes: [session.dateVotes]
      })
    })
  }

  override onFormSubmit() {
    if (this.sessionForm.valid) {
      let sessionToUpdate: SessionModel = this.sessionForm.value;
      const games = this.sessionForm.get("gameVotes")?.value || [];
      const foods = this.sessionForm.get("foodVotes")?.value || [];
      const dates = this.sessionForm.get("dateVotes")?.value || [];
      sessionToUpdate.gameVotes = games.map((game: GameModel) => ({
        voteoption: game
      }));
      sessionToUpdate.foodVotes = foods.map((food: string) => ({
        voteoption: food
      }))
      sessionToUpdate.dateVotes = dates.map((date: Date) => ({
        voteoption: date
      }))
      sessionToUpdate.userId = 1;
      this.sessionService.updateSession(sessionToUpdate).subscribe({
        next: () => {
          this.isSubmit = true;
          this.router.navigate(['sessionliste']);
        },
      });
    }
  }
}
