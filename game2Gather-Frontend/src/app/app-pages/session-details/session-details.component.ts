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
import {Dialog} from "primeng/dialog";
import {DateVoteModel} from "../../models/dateVote.model";
import {FoodVoteModel} from "../../models/foodVote.model";

@Component({
  selector: 'app-session-details',
  templateUrl: './session-details.component.html',
  styleUrl: './session-details.component.scss'
})
export class SessionDetailsComponent {

  sessionForm: FormGroup | undefined;
  session: SessionModel | undefined;
  gameVotesVotes = new FormGroup({});
  foodVotesVotes = new FormGroup({});
  dateVotesVotes = new FormGroup({});
  sessionID: string | null;

  constructor(private sessionService: SessionService,
              private router: Router,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private voteService: VoteService) {
    this.sessionID = this.route.snapshot.paramMap.get('session-id');
    if (this.sessionID) {
      this.loadSession(this.sessionID);
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
      dateVotes: new FormControl(session.dateVotes.map(vote => this.simplifyDate(vote.voteoption)))
    });
    this.addGameVoteVoteFormControls(session);
    this.addFoodVoteVoteFormControls(session);
    this.addDateVoteVoteFormControls(session);
  }

  private addGameVoteVoteFormControls(session: SessionModel) {
    for (const gameVote of session.gameVotes) {
      for (const player of session.players) {
        this.gameVotesVotes.addControl(`player${player.id}game${gameVote.id}`
          , new FormControl(
            gameVote.userVotes?.find(vote => vote.player.id == player.id)?.votevalue
          ),
        )
      }
    }
  }

  private addFoodVoteVoteFormControls(session: SessionModel) {
    for (const vote of session.foodVotes) {
      for (const player of session.players) {
        this.foodVotesVotes.addControl(`player${player.id}food${vote.id}`
          , new FormControl(
            vote.userVotes?.find(vote => vote.player.id == player.id)?.votevalue
          ),
        )
      }
    }
  }

  private addDateVoteVoteFormControls(session: SessionModel) {
    for (const vote of session.dateVotes) {
      for (const player of session.players) {
        this.dateVotesVotes.addControl(`player${player.id}date${vote.id}`
          , new FormControl(
            vote.userVotes?.find(vote => vote.player.id == player.id)?.votevalue
          ),
        )
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

  voteGame(player: PlayerModel, vote: GameVoteModel, clickEvent: SelectButtonOptionClickEvent) {
    if (vote.userVotes?.find(vote => vote.player.id === player.id)) {
      vote.userVotes?.forEach(vote => {
        if (vote.player.id === player.id) {
          vote.votevalue = clickEvent.option.value;
        }
      });
    } else {
      vote.userVotes?.push({player: player, votevalue: clickEvent.option.value});
    }
    this.voteService.voteGameVote(vote).subscribe();
  }

  voteFood(player: PlayerModel, vote: FoodVoteModel, clickEvent: SelectButtonOptionClickEvent) {
    if (vote.userVotes?.find(vote => vote.player.id === player.id)) {
      vote.userVotes?.forEach(vote => {
        if (vote.player.id === player.id) {
          vote.votevalue = clickEvent.option.value;
        }
      });
    } else {
      vote.userVotes?.push({player: player, votevalue: clickEvent.option.value});
    }
    this.voteService.voteFoodVote(vote).subscribe();
  }

  voteDate(player: PlayerModel, vote: DateVoteModel, clickEvent: SelectButtonOptionClickEvent) {
    if (vote.userVotes?.find(vote => vote.player.id === player.id)) {
      vote.userVotes?.forEach(vote => {
        if (vote.player.id === player.id) {
          vote.votevalue = clickEvent.option.value;
        }
      });
    } else {
      vote.userVotes?.push({player: player, votevalue: clickEvent.option.value});
    }
    this.voteService.voteDateVote(vote).subscribe();
  }

  addPlayer(value: string) {
    console.log("add")
    this.session!.players.push({id: undefined, username: value, session_id: this.sessionID!})
    this.sessionService.updateSession(this.session!).subscribe({
        next: () => {
          this.loadSession(this.sessionID!);
        },
      }
    )
  }

  simplifyDate(date: Date | string){
    return formatDate(new Date(date), 'dd/MM/yyyy HH:MM', 'en-US')
  };

  openDialog(userDialog: Dialog) {
    userDialog.visible = true;
  }

  routeToEdit(id: number | undefined) {
    this.router.navigateByUrl("sessionliste/editsession/" + id)
  }

}
