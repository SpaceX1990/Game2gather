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

  readonly gameId: number;

  override submitLabel = 'Spiel aktualisieren'

  constructor(private newInjector: Injector, private activatedRoute: ActivatedRoute) {
    super(newInjector);
    this.gameId = this.activatedRoute.snapshot.params['id'];
    this.getGameToUpdate();
  }

  private getGameToUpdate() {
    this.gameApiService.getGame(this.gameId).subscribe(value => {
      this.htmlTemplateName = `Spiel "${value.title}" bearbeiten`
      this.gameForm.patchValue(value);
    });
  }

  override onFormSubmit() {
    let updatedGame: GameModel = this.gameForm.value;
    updatedGame.id = this.gameId;
    if (this.gameForm.valid) {
      this.gameApiService.updateGame(updatedGame).subscribe(() => {
        this.routeToOverview();
      });
    }
  }
}
