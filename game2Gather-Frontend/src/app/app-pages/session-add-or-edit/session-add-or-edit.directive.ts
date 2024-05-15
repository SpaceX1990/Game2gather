import {Directive, Injector} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {SessionService} from "../../service/session.service";
import {Router} from "@angular/router";
import {GameModel} from "../../models/game.model";
import {GameService} from "../../service/game.service";
import {Observable} from "rxjs";

@Directive({
  selector: '[appSessionAddOrEdit]',
})
export abstract class SessionAddOrEditDirective {

  protected htmlTemplateName: string = "";
  protected sessionForm!: FormGroup;
  protected isSubmit: boolean = false;
  protected sessionService: SessionService;
  protected gameService: GameService;
  protected router: Router;
  protected formbuilder: FormBuilder;
  protected foodOptions: string[] = [];
  protected dateOptions: Date[] = [];
  protected gameList: GameModel[] = [];


  protected constructor(private injector: Injector) {
    this.sessionService = injector.get(SessionService);
    this.gameService = injector.get(GameService);
    this.router = injector.get(Router);
    this.formbuilder = injector.get(FormBuilder);
    this.getGames();
  }

  getGames() {
    this.gameService.getAllGames().subscribe(result => {
      this.gameList = result;
    });
  }

  onCancelClick() {
    this.router.navigate([''])
  }
}
