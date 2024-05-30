import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {GameVoteModel} from "../models/gameVote.model";
import {DateVoteModel} from "../models/dateVote.model";
import {FoodVoteModel} from "../models/foodVote.model";

@Injectable({
  providedIn: 'root'
})
export class VoteService {
  //service to send http-requests to the backend and access stored or create new GameVote-, DateVote or FoodVote-data


  constructor(private http: HttpClient) {
  }

  voteGameVote(vote: GameVoteModel): Observable<GameVoteModel[]> {
    return this.http.put<GameVoteModel[]>("api/gameVote/vote", vote);
  }

  voteDateVote(vote: DateVoteModel): Observable<DateVoteModel[]> {
    return this.http.put<DateVoteModel[]>("api/dateVote/vote", vote);
  }

  voteFoodVote(vote: FoodVoteModel): Observable<FoodVoteModel[]> {
    return this.http.put<FoodVoteModel[]>("api/foodVote/vote", vote);
  }

}
