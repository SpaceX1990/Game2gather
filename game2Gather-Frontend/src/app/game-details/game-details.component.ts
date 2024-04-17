import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {GameApiService} from "../service/game-api.service";
import {TagService} from "../service/tag.service";
import {GenreService} from "../service/genre.service";
import {HttpClient} from "@angular/common/http";
import {TagModel} from "../models/tag.model";
import {Genre} from "../models/genre.model";
import {GameModel} from "../models/game.model";
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrl: './game-details.component.scss'
})
export class GameDetailsComponent{

  tagsOptions: any[] | undefined = [];
  genresOptions: string | undefined;

  public gameForm: FormGroup | undefined;
  public gameId: number | undefined;
  public game: GameModel |undefined;
  constructor(private readonly _fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private gameApiService: GameApiService,
              private tagService: TagService,
              private genreService: GenreService,
              private http: HttpClient,
              public tag: TagModule) {
    this.tagsOptions = this.game?.tags;
    this.genresOptions = this.game?.genre;
    this.gameId =parseInt(route.snapshot.paramMap.get('game-id')!);
    this.readGame();
  }

  readGame() {
    this.gameApiService.readGame(this.gameId).subscribe(game => {
      this.game = game;
      this.setGameForm(game);
    })
  }

  private setGameForm(game: GameModel) {
    this.gameForm = new FormGroup({
      title: new FormControl(game.title),
      minPlayer: new FormControl(game.minPlayer),
      maxPlayer: new FormControl(game.maxPlayer),
      tags: new FormControl(game.tags),
      genre: new FormControl(game.genre)
    })
  }

  navToMainMenu() {
    this.router.navigateByUrl('/spielesammlung');
  }
}
