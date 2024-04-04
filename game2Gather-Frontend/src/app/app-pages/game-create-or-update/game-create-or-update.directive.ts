import {Directive, Injector} from '@angular/core';
import {Tag} from "../../models/tag.model";
import {Genre} from "../../models/genre.model";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {TagService} from "../../service/tag.service";
import {GenreService} from "../../service/genre.service";
import {GameApiService} from "../../service/game-api.service";

@Directive({
  selector: '[appGameCreateOrUpdate]'
})
export abstract class GameCreateOrUpdateDirective {

  tagsOptions: Tag[] = [];
  genresOptions: Genre[] = [];
  gameForm: FormGroup;
  protected _fb: FormBuilder;
  protected router: Router;
  protected tagService: TagService;
  protected genreService: GenreService;
  protected gameApiService: GameApiService;
  protected submitLabel = '';

  constructor(private injector: Injector) {
    this._fb = injector.get(FormBuilder)
    this.router = injector.get(Router)
    this.tagService = injector.get(TagService)
    this.genreService = injector.get(GenreService)
    this.gameApiService = injector.get(GameApiService)

    this.tagsOptions = this.tagService.getAll();
    this.genresOptions = this.genreService.getAll();
    this.gameForm = this._fb.group({
      title: '',
      minPlayer: 1,
      maxPlayer: new FormControl<number>(1, [Validators.max(15)]),
      tags: null as Tag[] | null,
      genre: ''
    })
  }

  onFormSubmit() {
  }
}
