import {UserVoteModel} from "./userVote.model";


export interface DateVoteModel {
  id?: number,
  voteoption: Date,
  votes?: UserVoteModel[],
  session_id?: number
}
