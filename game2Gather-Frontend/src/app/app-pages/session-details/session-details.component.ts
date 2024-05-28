import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {SessionService} from "../../service/session.service";
import {ActivatedRoute, Router} from "@angular/router";
import {SessionModel} from "../../models/session.model";
import {formatDate} from "@angular/common";
import {VoteValueEnum} from "../../models/voteValue.enum";
import {GameVoteModel} from "../../models/gameVote.model";
import {SelectButtonOptionClickEvent} from "primeng/selectbutton";
import {PlayerModel} from "../../models/player.model";
import {VoteService} from "../../service/vote.service";

@Component({
  selector: 'app-session-details',
  templateUrl: './session-details.component.html',
  styleUrl: './session-details.component.scss'
})
export class SessionDetailsComponent {

  sessionForm: FormGroup | undefined;
  session: SessionModel | undefined;
  gameVotesVotes = new FormGroup({});

  constructor(private sessionService: SessionService,
              private router: Router,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private voteService: VoteService) {
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
      dateVotes: new FormControl(session.dateVotes.map(vote => formatDate(new Date(vote.voteoption), 'dd/MM/yyyy HH:MM', 'en-US')))
    });
    this.addGameVoteVoteFormControls(session);
  }

  private addGameVoteVoteFormControls(session: SessionModel) {
    for (const gameVote of session.gameVotes) {
      if (gameVote.userVotes) {
        for (const vote of gameVote.userVotes) {
          this.gameVotesVotes.addControl(`player${vote.player.id}game${gameVote.voteoption.id}`, new FormControl(vote.votevalue))
        }
      }
    }
  }

  routeToOverview() {
    this.router.navigateByUrl('/sessionliste');
  }

  voteValueOptions: any[] = [
    {icon: "pi pi-thumbs-down", value: VoteValueEnum.NEGATIVE},
    {icon: "pi pi-bars", value: VoteValueEnum.NEUTRAL},
    {icon: "pi pi-thumbs-up", value: VoteValueEnum.POSITIVE},
  ]

  voteGame(player: PlayerModel, gameVote: GameVoteModel, clickEvent: SelectButtonOptionClickEvent) {

    if (gameVote.userVotes?.find(vote => vote.player.id === player.id)) {
      gameVote.userVotes?.forEach(vote => {
        if (vote.player.id === player.id) {
          vote.votevalue = clickEvent.option.value;
        }
      });
    } else {
      gameVote.userVotes?.push({player: player, votevalue: clickEvent.option.value});
    }

    this.voteService.voteGameVote(gameVote).subscribe();
  }

  protected readonly FormGroup = FormGroup;
}
