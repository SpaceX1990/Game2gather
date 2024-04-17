import { Injectable } from '@angular/core';
import {TagModel} from "../models/tag.model";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor() { }

  getAll(): TagModel[]{
    return [
      {
        id: 1,
        label: "elektroniczne"
      },
      {
        id: 2,
        label: "planszowki"
      }
    ]
  }
}
