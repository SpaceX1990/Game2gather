import {Component, Injector} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {SessionAddOrEditDirective} from "../session-add-or-edit.directive";
import {SessionModel} from "../../../models/session.model";
import {GameModel} from "../../../models/game.model";
import {GameVoteModel} from "../../../models/gameVote.model";
import {FoodVoteModel} from "../../../models/foodVote.model";

@Component({
  selector: 'app-session-edit',
  templateUrl: '../session-add-or-edit.component.html',
  styleUrls: ['../session-add-or-edit.component.scss']
})
export class SessionEditComponent extends SessionAddOrEditDirective {

  override submitLabel = "Session aktualisieren"
  private sessionToUpdate!: SessionModel;

  constructor(private newInjector: Injector, private route: ActivatedRoute) {
    super(newInjector);
    const sessionId = this.route.snapshot.paramMap.get('id');
    if (sessionId) {
      this.gameList.subscribe(list => this.loadSession(sessionId, list))
    }
  }

  loadSession(id: string, gameList: GameModel[]) {
    this.sessionService.getSessionById(id).subscribe((session: SessionModel) => {
      this.htmlTemplateName = `Session "${session.sessionTitle}" bearbeiten`;
      this.sessionToUpdate = session
      const gameVotes = session.gameVotes.map(vote => vote.voteoption);
      const selectedGames = gameVotes.map(selectedGame => gameList.find(game => game.id === selectedGame.id));
      this.sessionForm.patchValue({
        id: session.id,
        sessionTitle: session.sessionTitle,
        active: session.active,
        maxPlayer: session.maxPlayer,
        userId: session.userId,
        gameVotes: selectedGames,
        foodVotes: session.foodVotes.map(vote => vote.voteoption),
        dateVotes: session.dateVotes.map(vote => new Date(vote.voteoption)),
      });
    });
  }

  override onFormSubmit() {
    if (this.sessionForm.valid) {
      let updatedSession: SessionModel = this.sessionForm.value;
      const games = this.sessionForm.get("gameVotes")?.value || [];
      const foods = this.sessionForm.get("foodVotes")?.value || [];
      const dates = this.sessionForm.get("dateVotes")?.value || [];

      updatedSession.gameVotes = [
        //map preselected games to existing gameVoteModel else to empty voteobject
        ...games.map((selectedGame: GameModel): GameVoteModel => {
          return this.sessionToUpdate.gameVotes
            .find(vote => vote.voteoption.id === selectedGame.id) || {voteoption: selectedGame};
        })];

      updatedSession.foodVotes =  [
        //map preselected foods to existing gameFoodModel else to empty voteobject
        ...foods.map((selectedFoods: string): FoodVoteModel => {
          return this.sessionToUpdate.foodVotes
            .find(vote => vote.voteoption === selectedFoods) || {voteoption: selectedFoods};
        })];
      
      updatedSession.dateVotes = dates.map((date: any) => ({voteoption: date}));
      updatedSession.userId = 1;
      updatedSession.sessionVoteLink = this.sessionToUpdate.sessionVoteLink;

      this.sessionService.updateSession(updatedSession).subscribe({
        next: () => {
          this.isSubmit = true;
          this.router.navigate(['sessionliste']);
        },
      });
    }
  }
}
