import {VoteModel} from "./vote.model";


export interface DateVoteModel {
  id?: number,
  voteoption: Date,
  votes?: VoteModel[],
  session_id?: number
}
