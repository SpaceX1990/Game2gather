export interface SessionModel{
  id?: number,
  title: string,
  game: number,
  maxPlayer: number,
  genre: string,
  tags: TagModel[],
  image?: any
}
