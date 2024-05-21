export interface SessionModel {
  id: number;
  sessionTitle: string;
  active: boolean;
  maxPlayer: number;
  userId: number;
  gameOptions: any[];
  foodOptions: string[];
  dateOptions: Date[];
}
