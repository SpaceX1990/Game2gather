import {GameModel} from "./game.model";
import {VoteModel} from "./vote.model";


export interface GameVoteModel {
  id?: number,
  voteoption: GameModel,
  votes?: VoteModel[],
  session_id?: number
}
