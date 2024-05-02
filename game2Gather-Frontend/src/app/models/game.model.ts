import {TagModel} from "./tag.model";
import {GenreModel} from "./genre.model";

export interface GameModel{
  id?: number,
  title: string,
  minPlayer: number,
  maxPlayer: number,
  genre: GenreModel,
  tags: TagModel[],
  image?: any
}
