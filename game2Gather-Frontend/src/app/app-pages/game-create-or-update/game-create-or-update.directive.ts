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
  protected htmlTemplateName: string = "";

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

    //get all tags that can be saved in the game via tagService
    this.tagService.getAll().subscribe(tags => {
      this.tagsOptions = tags;
    });

    //get all genres that can be saved in the game via genreService
    this.genreService.getAll().subscribe(genres => {
      this.genresOptions = genres;
    });
  }

  //route to game collection
  routeToOverview() {
    this.router.navigateByUrl('/spielesammlung');
  }

  //create base function to use in html and override in directve-extending classes
  //to customize action on formSubmit
  onFormSubmit() {
  }
}
