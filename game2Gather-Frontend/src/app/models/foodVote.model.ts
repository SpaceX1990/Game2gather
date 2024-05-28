import {UserVoteModel} from "./userVote.model";


export interface FoodVoteModel {
  id?: number,
  voteoption: string,
  votes?: UserVoteModel[],
  session_id?: number
}
