import {Component} from '@angular/core';
import {GameCreateOrUpdateDirective} from "../game-create-or-update.directive";

@Component({
  selector: 'app-game-create',
  templateUrl: '../game-create-or update.template.html',
  styleUrl: '../game-create-or-update.style.scss'
})
export class GameCreateComponent extends GameCreateOrUpdateDirective {

  override submitLabel = 'Save new game';

  override onFormSubmit() {
    this.gameApiService.saveGame(this.gameForm.getRawValue()).subscribe(() => {

      this.router.navigateByUrl('/spielesammlung');
    })
  }
}

//TODO: komentarz, bild hochladen, design
