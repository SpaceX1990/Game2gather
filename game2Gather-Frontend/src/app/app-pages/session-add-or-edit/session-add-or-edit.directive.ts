import {Directive, Injector} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {SessionService} from "../../service/session.service";
import {Router} from "@angular/router";
import {GameModel} from "../../models/game.model";
import {GameApiService} from "../../service/game-api.service";

@Directive({
  selector: '[appSessionAddOrEdit]',
})
export abstract class SessionAddOrEditDirective {

  protected htmlTemplateName: string = "";
  protected sessionForm!: FormGroup;
  protected isSubmit: boolean = false;
  protected sessionService: SessionService;
  protected gameService: GameApiService;
  protected router: Router;
  protected formbuilder: FormBuilder;
  protected gameList: GameModel[] = [];


  protected constructor(private injector: Injector) {
    this.sessionService = injector.get(SessionService);
    this.gameService = injector.get(GameApiService);
    this.router = injector.get(Router);
    this.formbuilder = injector.get(FormBuilder);
    this.getGames();
  }

  getGames() {
    this.gameService.getAllGames().subscribe((result: GameModel[]) => {
      this.gameList = result;
    });
  }

  onFormSubmit() {

  }

  onCancelClick() {
    this.router.navigate([''])
  }
}
