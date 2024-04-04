import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {TagService} from "../../services/tag.service";
import {Tag} from "../models/tag.model";
import {Genre} from "../models/genre.model";
import {GenreService} from "../../services/genre.service";
import {HttpClient} from "@angular/common/http";
import {GameModel} from "../models/game.model";
import {GameService} from "../../services/game.service";

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrl: './create-game.component.scss'
})
export class CreateGameComponent {

  tagsOptions: Tag[] = [];
  genresOptions: Genre[] = [];

  public gameForm = this._fb.group({
    title: '',
    minimumPlayers: 1,
    maximumPlayers: new FormControl<number>(1, [Validators.max(15)]),
    tags: null as Tag[] | null,
    genre: null as number | null
  });

  constructor(private readonly _fb: FormBuilder,
              private router: Router,
              private tagService: TagService,
              private genreService: GenreService,
              private gameService: GameService,
              private http: HttpClient) {
    this.tagsOptions = this.tagService.getAll();
    this.genresOptions = this.genreService.getAll();
  }

  saveNewGame() {
    this.gameService.create(this.gameForm.getRawValue()).subscribe(data =>{

      this.router.navigateByUrl('/spielesammlung');
    })
  }
}

//TODO: komentarz, bild hochladen, design
