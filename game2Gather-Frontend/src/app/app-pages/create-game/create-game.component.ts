import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {TagService} from "../../service/tag.service";
import {TagModel} from "../../models/tag.model";
import {Genre} from "../../models/genre.model";
import {GenreService} from "../../service/genre.service";
import {HttpClient} from "@angular/common/http";
import {GameModel} from "../../models/game.model";
import {GameApiService} from "../../service/game-api.service";

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrl: './create-game.component.scss'
})
export class CreateGameComponent {

  tagsOptions: TagModel[] = [];
  genresOptions: Genre[] = [];

  public gameForm = this._fb.group({
    title: new FormControl<String>('', [Validators.required]),
    minimumPlayers: new FormControl<number>(1, [Validators.required, Validators.min(1), Validators.pattern(/^\d+$/)]),
    maximumPlayers: new FormControl<number>(1, [Validators.required, Validators.max(15), Validators.pattern(/^\d+$/)]),
    tags: null as TagModel[] | null,
    genre: null as Genre[] | null
  });

  constructor(private readonly _fb: FormBuilder,
              private router: Router,
              private tagService: TagService,
              private genreService: GenreService,
              private gameService: GameApiService,
              private http: HttpClient) {
     this.tagService.getAll().subscribe(tags=>{
       this.tagsOptions = tags;
     });

     this.genreService.getAll().subscribe(genres =>{
       this.genresOptions = genres;
     });

  }

  saveNewGame() {
    this.gameService.saveGame(this.gameForm.getRawValue()).subscribe(data =>{

      this.router.navigateByUrl('/spielesammlung');
    })
  }
}

//TODO: bild hochladen, design
