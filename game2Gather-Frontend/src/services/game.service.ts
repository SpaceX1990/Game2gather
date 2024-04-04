import { Injectable } from '@angular/core';
import {GameModel} from "../app/models/game.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient) { }

  create(game: any): Observable<GameModel> {
    return this.http.post<GameModel>('/game',game);
  }
}
