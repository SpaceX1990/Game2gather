import {Component} from '@angular/core';
import {GameCreateOrUpdateDirective} from "../game-create-or-update.directive";

@Component({
  selector: 'app-game-create',
  templateUrl: '../game-create-or update.template.html',
  styleUrl: '../game-create-or-update.style.scss'
})
export class GameCreateComponent extends GameCreateOrUpdateDirective {
  //extend base-directive of game creation or update with custom page-title and submit-label

  override submitLabel = 'Spiel erstellen';
  override htmlTemplateName = "Neues Spiel anlegen"

  override onFormSubmit() {
    if (this.gameForm.valid) {
      //if the entered details are valid, save the game via gameApiService and route to the gameCollectionPage after
      this.gameApiService.saveGame(this.gameForm.getRawValue()).subscribe(() => {
        this.routeToOverview();
      })
    }
  }
}
