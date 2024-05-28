import {Component} from '@angular/core';
import {GameCreateOrUpdateDirective} from "../game-create-or-update.directive";

@Component({
  selector: 'app-game-create',
  templateUrl: '../game-create-or update.template.html',
  styleUrl: '../game-create-or-update.style.scss'
})
export class GameCreateComponent extends GameCreateOrUpdateDirective {

  override submitLabel = 'Neues Spiel speichern';

  override onFormSubmit() {
    if (this.gameForm.valid) {
      this.gameApiService.saveGame(this.gameForm.getRawValue()).subscribe(() => {
        this.routeToOverview();
      })
    }
  }
}
