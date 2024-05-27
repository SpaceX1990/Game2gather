import { FormBuilder, FormGroup} from '@angular/forms';
import { SessionService } from '../../service/session.service';
import { Router } from '@angular/router';
import { GameModel } from '../../models/game.model';
import { GameApiService } from '../../service/game-api.service';
import { Injector, Directive} from '@angular/core';

@Directive({
  selector: '[appSessionAddOrEdit]',
})
export abstract class SessionAddOrEditDirective {

  protected htmlTemplateName: string = "";
  protected submitLabel: string = "";

  protected sessionForm!: FormGroup;
  protected isSubmit: boolean = false;
  protected sessionService: SessionService;
  protected gameService: GameApiService;
  protected router: Router;
  protected formBuilder: FormBuilder;
  protected gameList: GameModel[] = [];


  protected constructor(private injector: Injector) {
    this.sessionService = injector.get(SessionService);
    this.gameService = injector.get(GameApiService);
    this.router = injector.get(Router);
    this.formBuilder = injector.get(FormBuilder);
    this.getGames();
    this.sessionForm = this.formBuilder.group({
      id: [''],
      sessionTitle: [''],
      active: true,
      maxPlayer: [''],
      userId: null,
      gameVotes: [[]],
      foodVotes: [[]],
      dateVotes: [[]],
    })
  }

  getGames() {
    this.gameService.getAllGames().subscribe((result: GameModel[]) => {
      this.gameList = result;
    });
  }

  onFormSubmit() {}
  routeToOverview() {
    this.router.navigateByUrl('/sessionliste');
  }

  onCancelClick() {
    this.router.navigate(['']);
  }
}
