export interface SessionModel {
  id: number,
  sessionTitle: string,
  active: boolean,
  sessionVoteLink: string,
  maxPlayer: number,
  userId?: number
}
