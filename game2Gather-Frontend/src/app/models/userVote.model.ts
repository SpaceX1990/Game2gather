import {PlayerModel} from "./player.model";

export interface UserVoteModel {
  id?: number,
  votevalue: string,
  player: PlayerModel
}
