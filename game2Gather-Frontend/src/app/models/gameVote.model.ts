import {GameModel} from "./game.model";
import {UserVoteModel} from "./userVote.model";


export interface GameVoteModel {
  id?: number,
  voteoption: GameModel,
  votes?: UserVoteModel[],
  session_id?: number
}
