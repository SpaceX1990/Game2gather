import {Component, OnInit} from '@angular/core';
import {GameModel} from "../../models/game.model";
import {Router} from "@angular/router";
import {GameApiService} from "../../service/game-api.service";
import {ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-game-collection',
  templateUrl: './game-collection.component.html',
  styleUrl: './game-collection.component.scss'
})
export class GameCollectionComponent implements OnInit{
  protected isSidebarVisible: boolean = false;
  // protected game: GameModel;
  protected gameCollection: GameModel[] = [
    /*{
      id: 1,
      title: "testTitle1"
      , genre: "testGenre1"
      , tags: ["string", "baum", "meh"],
      minPlayer: 1,
      maxPlayer: 3
    },{
      id: 2,
      title: "testTitle1"
      , genre: "testGenre1"
      , tags: ["string", "baum", "meh"],
      minPlayer: 1,
      maxPlayer: 3
    },{
      id: 3,
      title: "Ich mag Bäume"
      , genre: "Baum"
      , tags: ["tag1", "tag2", "tag3"],
      minPlayer: 4,
      maxPlayer: 10
    },{
      id: 3,
      title: "Ich mag Bäume"
      , genre: "Baum"
      , tags: ["tag1", "tag2", "tag3"],
      minPlayer: 4,
      maxPlayer: 10
    },{
      id: 3,
      title: "Ich mag Bäume"
      , genre: "Baum"
      , tags: ["tag1", "tag2", "tag3"],
      minPlayer: 4,
      maxPlayer: 10
    }*/
  ]

  constructor(private gameApiService: GameApiService,
              private confirmationService: ConfirmationService,
              private router : Router) {
  }

  ngOnInit() {
    this.getGames()
  }

  // getGame(game: GameModel) {
  //   this.gameApiService.readGame(game?.id).subscribe({
  //       // this.game = result;
  //   });
  // }

  getGames() {
    this.gameApiService.getAllGames().subscribe(result => {
      this.gameCollection = result;
    });
  }

  deleteGame(game: GameModel | undefined) {
      this.confirmationService.confirm({
        dismissableMask: true,
        message:`Soll das Spiel ${game?.title} wirklich gelöscht werden?`,
        header:"Spiel löschen",
        acceptIcon: "none",
        acceptLabel: "Löschen",
        acceptButtonStyleClass: "p-button-danger",
        rejectIcon: "none",
        rejectLabel: "Abbrechen",
        closeOnEscape: false,
        defaultFocus: "reject",
        accept: () => {
          this.gameApiService.deleteGame(game?.id).subscribe({
            next: () => {
              this.getGames();
            },
          })
        },
      })
  }
}
