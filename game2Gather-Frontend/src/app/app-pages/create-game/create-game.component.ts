import {Component} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {TagService} from "../../service/tag.service";
import {TagModel} from "../../models/tag.model";
import {GenreModel} from "../../models/genre.model";
import {GenreService} from "../../service/genre.service";
import {HttpClient} from "@angular/common/http";
import {GameApiService} from "../../service/game-api.service";


@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrl: './create-game.component.scss'
})
export class CreateGameComponent {

  //TODO: check for validators for required fields before submitting

  tagsOptions: TagModel[] = [];
  genresOptions: GenreModel[] = [];

  public gameForm = this._fb.group({
    title: new FormControl<String>('', [Validators.required]),
    minimumPlayers: new FormControl<number>(1, [Validators.required, Validators.min(1), Validators.pattern(/^\d+$/)]),
    maximumPlayers: new FormControl<number>(1, [Validators.required, Validators.min(1), Validators.max(15), Validators.pattern(/^\d+$/)]),
    tags: null as TagModel[] | null,
    genre: null as GenreModel | null
  });

  constructor(private readonly _fb: FormBuilder,
              private router: Router,
              private tagService: TagService,
              private genreService: GenreService,
              private gameService: GameApiService,
              private http: HttpClient) {
    this.tagService.getAll().subscribe(tags => {
      this.tagsOptions = tags;
    });

    this.genreService.getAll().subscribe(genres => {
      this.genresOptions = genres;
    });

  }

  saveNewGame() {
    this.gameService.saveGame(this.gameForm.getRawValue()).subscribe(data => {

      this.router.navigateByUrl('/spielesammlung');
    })
  }
}


