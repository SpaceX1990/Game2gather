import {Component} from '@angular/core';
import {SessionModel} from "../../models/SessionModel";

@Component({
  selector: 'app-sessionlist',
  templateUrl: './sessionlist.component.html',
  styleUrls: ['./sessionlist.component.scss']
})
export class SessionlistComponent {

  sessions: SessionModel | undefined;

  /*
  sessions: SessionModel[] = [
    {id: 12, sessionTitel: 'Catan'},
    {id: 13, sessionTitel: 'Siedler von Catan'},
    {id: 14, sessionTitel: 'Pandemic Legacy'},
    {id: 15, sessionTitel: 'Ticket to Ride'},
    {id: 16, sessionTitel: 'Carcassonne'},
    {id: 17, sessionTitel: 'Scrabble'},
    {id: 18, sessionTitel: 'Codenames'},
    {id: 19, sessionTitel: 'Risiko'},
    {id: 20, sessionTitel: 'Schach'}
  ];*/


  showdeleteDialog() {

  }
}
