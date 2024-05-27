import {Component, Injector, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {SessionAddOrEditDirective} from "../session-add-or-edit.directive";
import {SessionModel} from "../../../models/session.model";

@Component({
  selector: 'app-session-edit',
  templateUrl: '../session-add-or-edit.component.html',
  styleUrls: ['../session-add-or-edit.component.scss']
})
export class SessionEditComponent extends SessionAddOrEditDirective implements OnInit {

  override submitLabel = "Session aktualisieren"

  constructor(private newInjector: Injector, private route: ActivatedRoute) {
    super(newInjector);
  }

  ngOnInit() {
    const sessionId = this.route.snapshot.paramMap.get('id');
    if (sessionId) {
      this.loadSession(sessionId);
    }
  }

  loadSession(id: string) {
    this.sessionService.getSessionById(id).subscribe((session: SessionModel) => {
      this.htmlTemplateName = `Session "${session.sessionTitle}" bearbeiten`;
      const gameVotes = session.gameVotes.map(vote => vote.voteoption);
      const foodVotes = session.foodVotes.map(vote => vote.voteoption);
      const dateVotes = session.dateVotes.map(vote => new Date(vote.voteoption));

      this.sessionForm.patchValue({
        id: session.id,
        sessionTitle: session.sessionTitle,
        active: session.active,
        maxPlayer: session.maxPlayer,
        userId: session.userId,
        gameVotes: gameVotes,
        foodVotes: foodVotes,
        dateVotes: dateVotes,
      });
    });
  }

  override onFormSubmit() {
    if (this.sessionForm.valid) {
      let updatedSession: SessionModel = this.sessionForm.value;
      const games = this.sessionForm.get("gameVotes")?.value || [];
      const foods = this.sessionForm.get("foodVotes")?.value || [];
      const dates = this.sessionForm.get("dateVotes")?.value || [];
      updatedSession.gameVotes = games.map((game: any) => ({voteoption: game}));
      updatedSession.foodVotes = foods.map((food: any) => ({voteoption: food}));
      updatedSession.dateVotes = dates.map((date: any) => ({voteoption: date}));
      updatedSession.userId = 1;

      this.sessionService.updateSession(updatedSession).subscribe({
        next: () => {
          this.isSubmit = true;
          this.router.navigate(['sessionliste']);
        },
      });
    }
  }
}
