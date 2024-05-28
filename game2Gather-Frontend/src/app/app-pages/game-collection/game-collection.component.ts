import {Component, OnInit} from '@angular/core';
import {GameModel} from "../../models/game.model";
import {GameApiService} from "../../service/game-api.service";
import {ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-game-collection',
  templateUrl: './game-collection.component.html',
  styleUrl: './game-collection.component.scss'
})
export class GameCollectionComponent implements OnInit{
  protected isSidebarVisible: boolean = false;
  protected gameCollection: GameModel[] = [

  ]

  constructor(private gameApiService: GameApiService, private confirmationService: ConfirmationService) {
  }

  ngOnInit() {
    this.getGames()
  }

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
