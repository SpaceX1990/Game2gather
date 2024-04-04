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

  override submitLabel = 'Update game'

  constructor(private newInjector: Injector, private activatedRoute: ActivatedRoute) {
    super(newInjector);
    this.gameId = this.activatedRoute.snapshot.params['id'];
    this.getGameToUpdate();
  }

  private getGameToUpdate() {
    this.gameApiService.getGame(this.gameId).subscribe(value => {

      //only temporary till testadta for genre exists
      if (this.genresOptions.filter((genre) => genre.label === value.genre ).length === 0) {
        this.genresOptions.push({id: 1, label: value.genre});
      }

      this.gameForm.patchValue(value);
    });
  }

  override onFormSubmit() {
    let updatedGame: GameModel = this.gameForm.value;
    updatedGame.id = this.gameId;
    this.gameApiService.updateGame(updatedGame).subscribe();
  }

}
