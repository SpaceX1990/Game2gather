import { Injectable } from '@angular/core';
import {Tag} from "../app/models/tag.model";

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor() { }

  getAll(): Tag[]{
    return [
      {
        id: 1,
        label: "strategy"
      },
      {
        id: 2,
        label: "deckbuilder"
      },
      {
        id: 3,
        label: "drafting"
      },
      {
        id: 4,
        label: "memory"
      },
      {
        id: 5,
        label: "dungeon"
      },
      {
        id: 6,
        label: "dice game"
      },
      {
        id: 7,
        label: "card game"
      }
    ]
  }
}
