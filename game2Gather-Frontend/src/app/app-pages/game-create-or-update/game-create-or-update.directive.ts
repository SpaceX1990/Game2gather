import {Directive, Injector} from '@angular/core';
import {TagModel} from "../../models/tag.model";
import {GenreModel} from "../../models/genre.model";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {TagService} from "../../service/tag.service";
import {GenreService} from "../../service/genre.service";
import {GameApiService} from "../../service/game-api.service";

@Directive({
  selector: '[appGameCreateOrUpdate]'
})
export abstract class GameCreateOrUpdateDirective {

  tagsOptions: TagModel[] = [];
  genresOptions: GenreModel[] = [];
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
    this.gameForm = this._fb.group({
      title: new FormControl<String>("", [Validators.required]),
      minPlayer: new FormControl<number>(1, [Validators.required]),
      maxPlayer: new FormControl<number>(4, [Validators.required]),
      tags: null as TagModel[] | null,
      genre: null as GenreModel | null
    })

    this.tagService.getAll().subscribe(tags => {
      this.tagsOptions = tags;
    });

    this.genreService.getAll().subscribe(genres => {
      this.genresOptions = genres;
    });
  }

  routeToOverview() {
    this.router.navigateByUrl('/spielesammlung');
  }

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 && (c1.id === c2.id || c2 === c1.id);
  }

  onFormSubmit() {
  }
}
