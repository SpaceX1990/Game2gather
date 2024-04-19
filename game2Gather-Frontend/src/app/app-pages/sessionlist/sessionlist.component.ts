import {Component, OnInit} from '@angular/core';
import {SessionModel} from "../../models/SessionModel";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app-sessionlist',
  templateUrl: './sessionlist.component.html',
  styleUrls: ['./sessionlist.component.scss']
})
export class SessionlistComponent implements OnInit{

  activeSessions: SessionModel[] | any;
  pastSessions: SessionModel[] | any;

  constructor(private sessionService: SessionService) {
  }

  ngOnInit() {
    this.getAllActiveSessions()
  }

  getAllActiveSessions() {
    this.sessionService.getAllActiveSessios().subscribe(sessions => {
      this.activeSessions = sessions
    })
  }

  getAllPastSessions() {

  }

  showdeleteDialog() {

  }
}
