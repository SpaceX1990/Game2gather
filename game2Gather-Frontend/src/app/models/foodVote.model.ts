import {VoteModel} from "./vote.model";


export interface FoodVoteModel {
  id?: number,
  voteoption: string,
  votes?: VoteModel[],
  session_id?: number
}
