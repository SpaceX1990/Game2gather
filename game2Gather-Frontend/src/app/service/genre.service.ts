import { Injectable } from '@angular/core';
import {Tag} from "../models/tag.model";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor() { }

  getAll(): Tag[]{
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
