import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GameModel} from "../models/game.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GameApiService {

  constructor(private http: HttpClient) {
  }

  getAllGames(): Observable<GameModel[]> {
    return this.http.get<GameModel[]>("/api/game");
  }

  readGame(id: number | undefined) :Observable<GameModel> {
    return this.http.get<GameModel>("/api/game/read/" + id)
  }

  deleteGame(id: number | undefined) {
    return this.http.delete("/api/game/delete/" + id);
  }

  saveGame(game: any): Observable<GameModel> {
    return this.http.post<GameModel>('/api/game', game);
  }

  getGame(id: number): Observable<GameModel> {
    return this.http.get<GameModel>("/api/game/" + id);
  }

  updateGame(game: GameModel): Observable<GameModel> {
    return this.http.put<GameModel>("/api/game", game);
  }

}
