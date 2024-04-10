import {Component} from '@angular/core';
import {SessionModel} from "../../models/SessionModel";

@Component({
  selector: 'app-sessionlist',
  templateUrl: './sessionlist.component.html',
  styleUrls: ['./sessionlist.component.scss']
})
export class SessionlistComponent {

  session: SessionModel | undefined;

  sessions: SessionModel[] = [
    {id: 12, sessionTitel: 'Catan', maxPlayer: 5},
    {id: 13, sessionTitel: 'Siedler von Catan', maxPlayer: 5},
    {id: 14, sessionTitel: 'Pandemic Legacy', maxPlayer: 5},
    {id: 15, sessionTitel: 'Ticket to Ride', maxPlayer: 3},
    {id: 16, sessionTitel: 'Carcassonne', maxPlayer: 1},
    {id: 17, sessionTitel: 'Scrabble'},
    {id: 18, sessionTitel: 'Codenames'},
    {id: 19, sessionTitel: 'Risiko', maxPlayer: 4},
    {id: 20, sessionTitel: 'Schach', maxPlayer: 2}
  ];


  showdeleteDialog() {

  }
}
