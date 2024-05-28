import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {GameVoteModel} from "../models/gameVote.model";

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor(private http: HttpClient) {
  }

  voteGameVote(gameVote: GameVoteModel): Observable<GameVoteModel[]> {
    return this.http.put<GameVoteModel[]>("api/gameVote/vote", gameVote);
  }

}
