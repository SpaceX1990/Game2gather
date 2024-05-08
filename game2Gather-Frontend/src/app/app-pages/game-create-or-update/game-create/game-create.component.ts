import {Component} from '@angular/core';
import {GameCreateOrUpdateDirective} from "../game-create-or-update.directive";
import {FormControl, Validators} from "@angular/forms";
import {TagModel} from "../../../models/tag.model";
import {GenreModel} from "../../../models/genre.model";

@Component({
  selector: 'app-game-create',
  templateUrl: '../game-create-or update.template.html',
  styleUrl: '../game-create-or-update.style.scss'
})
export class GameCreateComponent extends GameCreateOrUpdateDirective {

  override submitLabel = 'Save new game';

  override onFormSubmit() {
    if (this.gameForm.valid) {
      this.gameApiService.saveGame(this.gameForm.getRawValue()).subscribe(() => {
        this.routeToOverview();
      })
    }
  }
//TODO: validators
  override gameForm = this._fb.group({
    title: new FormControl<String>('', [Validators.required]),
    minPlayer: new FormControl<number>(1, [Validators.required, Validators.min(1), Validators.pattern(/^\d+$/)]),
    maxPlayer: new FormControl<number>(1, [Validators.required, Validators.min(1), Validators.max(15), Validators.pattern(/^\d+$/)]),
    tags: null as TagModel[] | null,
    genre: null as GenreModel | null
  });

}

//TODO: komentarz, bild hochladen, design
