export interface GameModel{
  id?: number,
  title: string,
  minPlayer: number,
  maxPlayer: number,
  genre: string,
  tags: any[],
  image?: any
}
