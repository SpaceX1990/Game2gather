import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GameModel} from "../models/game.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GameApiService {

  constructor(private http: HttpClient) { }

  getAllGames(): Observable<GameModel[]> {
    return this.http.get<GameModel[]>("/api/game");
  }

  deleteGame(id: number | undefined) {
    return this.http.delete("/api/game/delete/" + id);
  }
}
