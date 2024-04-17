import {TagModel} from "./tag.model";

export interface GameModel{
  id?: number,
  title: string,
  minPlayer: number,
  maxPlayer: number,
  genre: string,
  tags: TagModel[],
  image?: any
}
