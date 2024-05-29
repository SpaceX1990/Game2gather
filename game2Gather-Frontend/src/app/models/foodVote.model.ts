import {UserVoteModel} from "./userVote.model";


export interface FoodVoteModel {
  id?: number,
  voteoption: string,
  userVotes?: UserVoteModel[],
  session_id?: number
}
