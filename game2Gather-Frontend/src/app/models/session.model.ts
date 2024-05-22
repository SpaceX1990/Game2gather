import {GameVoteModel} from "./gameVote.model";
import {FoodVoteModel} from "./foodVote.model";
import {DateVoteModel} from "./dateVote.model";

export interface SessionModel {
  id: number;
  sessionTitle: string;
  active: boolean;
  maxPlayer: number;
  userId: number;
  gameVotes: GameVoteModel[];
  foodVotes: FoodVoteModel[];
  dateVotes: DateVoteModel[];
}
