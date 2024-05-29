import {UserVoteModel} from "./userVote.model";


export interface DateVoteModel {
  id?: number,
  voteoption: Date,
  userVotes?: UserVoteModel[],
  session_id?: number
}
