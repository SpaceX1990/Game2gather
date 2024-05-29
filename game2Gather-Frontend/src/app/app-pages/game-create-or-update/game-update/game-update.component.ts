import {Component, Injector} from '@angular/core';
import {GameCreateOrUpdateDirective} from "../game-create-or-update.directive";
import {ActivatedRoute} from "@angular/router";
import {GameModel} from "../../../models/game.model";

@Component({
  selector: 'app-game-update',
  templateUrl: '../game-create-or update.template.html',
  styleUrl: '../game-create-or-update.style.scss'
})
export class GameUpdateComponent extends GameCreateOrUpdateDirective {
  //extend base-directive of game creation or update with custom page-title and submit-label

  readonly gameId: number;

  override submitLabel = 'Spiel aktualisieren'

  constructor(private newInjector: Injector, private activatedRoute: ActivatedRoute) {
    super(newInjector);
    //get the id for the game to edit from current url
    this.gameId = this.activatedRoute.snapshot.params['id'];
    this.getGameToUpdate();
  }

  private getGameToUpdate() {
    //get the game to update via gameApiService and insert the data into the gameForm and set the pageTitle
    this.gameApiService.getGame(this.gameId).subscribe(value => {
      this.htmlTemplateName = `Spiel "${value.title}" bearbeiten`
      this.gameForm.patchValue(value);
    });
  }

  override onFormSubmit() {
    let updatedGame: GameModel = this.gameForm.value;
    updatedGame.id = this.gameId;
    if (this.gameForm.valid) {
      //if the entered details are valid, save the game via gameApiService and route to the gameCollectionPage after
      this.gameApiService.updateGame(updatedGame).subscribe(() => {
        this.routeToOverview();
      });
    }
  }
}
