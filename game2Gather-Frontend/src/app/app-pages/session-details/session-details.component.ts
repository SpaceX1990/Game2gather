import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {SessionService} from "../../service/session.service";
import {ActivatedRoute, Router} from "@angular/router";
import {SessionModel} from "../../models/session.model";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-session-details',
  templateUrl: './session-details.component.html',
  styleUrl: './session-details.component.scss'
})
export class SessionDetailsComponent {

  sessionForm: FormGroup | undefined;
  session: SessionModel | undefined;

  constructor(private sessionService: SessionService, private router: Router, private formBuilder: FormBuilder, private route: ActivatedRoute) {
    const sessionId = this.route.snapshot.paramMap.get('session-id');
    if (sessionId) {
      this.loadSession(sessionId);
    }
  }

  loadSession(id: string) {
    this.sessionService.getSessionById(id).subscribe((session: SessionModel) => {
      this.session = session
      this.setSessionForm(session);
    });
  }

  private setSessionForm(session: SessionModel) {
    const gameVotes = session.gameVotes.map(vote => vote.voteoption);
    const selectedGames = gameVotes.map(selectedGame => selectedGame.title);
    this.sessionForm = new FormGroup({
      active: new FormControl(session.active ? "Aktiv" : "Beendet"),
      title: new FormControl(session.sessionTitle),
      maxPlayer: new FormControl(session.maxPlayer),
      userId: new FormControl(session.userId),
      gameVotes: new FormControl(selectedGames),
      foodVotes: new FormControl(session.foodVotes.map(vote => vote.voteoption)),
      dateVotes: new FormControl(session.dateVotes.map(vote => formatDate(new Date(vote.voteoption),'dd/MM/yyyy HH:MM','en-US')))
    });
  }

  routeToOverview() {
    this.router.navigateByUrl('/sessionliste');
  }

  protected readonly formatDate = formatDate;
}
