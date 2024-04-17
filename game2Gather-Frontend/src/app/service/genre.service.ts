import { Injectable } from '@angular/core';
import {Genre} from "../models/genre.model";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor() { }

  getAll(): Genre[]{
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
