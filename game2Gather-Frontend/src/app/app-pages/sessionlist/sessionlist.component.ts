import {Component, OnInit} from '@angular/core';
import {SessionModel} from "../../models/SessionModel";
import {SessionService} from "../../services/session.service";
import {ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-sessionlist',
  templateUrl: './sessionlist.component.html',
  styleUrls: ['./sessionlist.component.scss']
})
export class SessionlistComponent implements OnInit {

  activeSessions: SessionModel[] | any;
  pastSessions: SessionModel[] | any;

  constructor(private sessionService: SessionService, private confirmationsService: ConfirmationService) {
  }

  ngOnInit() {
    this.getAllActiveSessions();
    this.getAllPastSessions();
  }

  getAllActiveSessions() {
    this.sessionService.getAllActiveSessios().subscribe(sessions => {
      this.activeSessions = sessions
    })
  }

  getAllPastSessions() {
    this.sessionService.getAllPastSessios().subscribe(sessions => {
      this.pastSessions = sessions;
    })
  }

  deleteSession(session: SessionModel | undefined) {
    this.confirmationsService.confirm({
      dismissableMask: true,
      message:`Soll die Session ${session?.sessionTitle} wirklich gelöscht werden?`,
      header:"Session löschen",
      acceptIcon: "none",
      acceptLabel: "Löschen",
      acceptButtonStyleClass: "p-button-danger",
      rejectIcon: "none",
      rejectLabel: "Abbrechen",
      closeOnEscape: false,
      defaultFocus: "reject",
      accept: () => {
        this.sessionService.deleteSession(session?.id).subscribe({
          next: () => {
            this.getAllActiveSessions();
            this.getAllPastSessions();
          }
        })
      },
    })
  }
}
