import {GameVoteModel} from "./gameVote.model";
import {FoodVoteModel} from "./foodVote.model";
import {DateVoteModel} from "./dateVote.model";
import {PlayerModel} from "./player.model";

export interface SessionModel {
  id: number;
  sessionTitle: string;
  active: boolean;
  maxPlayer: number;
  userId: number;
  players: PlayerModel[];
  gameVotes: GameVoteModel[];
  foodVotes: FoodVoteModel[];
  dateVotes: DateVoteModel[];
  sessionVoteLink: string;
}
