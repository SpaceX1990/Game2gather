import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SessionService} from '../../service/session.service';
import {Router} from '@angular/router';
import {GameModel} from '../../models/game.model';
import {GameApiService} from '../../service/game-api.service';
import {Directive, Injector} from '@angular/core';
import {Observable, of} from "rxjs";

@Directive({
  selector: '[appSessionAddOrEdit]',
})
export abstract class SessionAddOrEditDirective{

  protected htmlTemplateName: string = "";
  protected submitLabel: string = "";

  protected sessionForm!: FormGroup;
  protected isSubmit: boolean = false;
  protected sessionService: SessionService;
  protected gameService: GameApiService;
  protected router: Router;
  protected formBuilder: FormBuilder;
  protected gameList: Observable<GameModel[]> = of([]);


  protected constructor(private injector: Injector) {
    this.sessionService = injector.get(SessionService);
    this.gameService = injector.get(GameApiService);
    this.router = injector.get(Router);
    this.formBuilder = injector.get(FormBuilder);
    this.gameList = this.gameService.getAllGames();
    //build basic-session-form to create session
    this.sessionForm = this.formBuilder.group({
      id: [''],
      sessionTitle: ['', Validators.required],
      active: true,
      maxPlayer: ['', Validators.required],
      userId: null,
      gameVotes: [[]],
      foodVotes: [[]],
      dateVotes: [[]],
    })
  }

  //basic formSubmit to customize in directive-extending classes
  onFormSubmit() {
  }

  routeToOverview() {
    this.router.navigateByUrl('/sessionliste');
  }

}
