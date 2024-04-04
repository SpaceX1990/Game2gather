import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {GameFormComponent} from "../game-form/game-form.component";
import {GameApiService} from "../service/game-api.service";
import {TagService} from "../service/tag.service";
import {GenreService} from "../service/genre.service";
import {HttpClient} from "@angular/common/http";
import {Tag} from "../models/tag.model";
import {Genre} from "../models/genre.model";
import {GameModel} from "../models/game.model";

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrl: './game-details.component.scss'
})
export class GameDetailsComponent{

  // tagsOptions: Tag[] = [];
  // genresOptions: Genre[] = [];

  public gameForm = this._fb.group({
    title: null,
    minPlayer: null,
    maxPlayer: null,
    tags: null,
    genre: null
  })
  constructor(private readonly _fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private gameApiService: GameApiService,
              private tagService: TagService,
              private genreService: GenreService,
              private http: HttpClient) {
    // this.tagsOptions = this.tagService.getAll();
    // this.genresOptions = this.genreService.getAll();
    this.readGame();
  }

  readGame(game: GameModel) {
    this.gameApiService.readGame(game.id).subscribe({

    })
  }
}
