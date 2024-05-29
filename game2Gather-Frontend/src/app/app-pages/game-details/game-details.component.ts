import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {GameApiService} from "../../service/game-api.service";
import {GameModel} from "../../models/game.model";

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent {
  public gameForm: FormGroup | undefined;
  public gameId: number | undefined;
  public game: GameModel | undefined;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private gameApiService: GameApiService,
  ) {
    this.gameId = parseInt(route.snapshot.paramMap.get('game-id')!, 10);
    this.readGame();
  }

  //get game to show via gameApiService as Observable and set game to result
  readGame() {
    this.gameApiService.readGame(this.gameId).subscribe(game => {
      this.game = game;
      this.setGameForm(game);
    });
  }

  //set values into form to show details
  private setGameForm(game: GameModel) {
    this.gameForm = new FormGroup({
      title: new FormControl(game.title),
      minPlayer: new FormControl(game.minPlayer),
      maxPlayer: new FormControl(game.maxPlayer),
      tags: new FormControl(game.tags),
      genre: new FormControl(game.genre.label)
    });
  }

  routeToOverview() {
    this.router.navigateByUrl('/spielesammlung');
  }
}
