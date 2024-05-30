import {Component, OnInit} from '@angular/core';
import {GameModel} from "../../models/game.model";
import {GameApiService} from "../../service/game-api.service";
import {ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-game-collection',
  templateUrl: './game-collection.component.html',
  styleUrl: './game-collection.component.scss'
})
export class GameCollectionComponent implements OnInit {
  protected isSidebarVisible: boolean = false;
  protected gameCollection: GameModel[] = []
  protected searchTerm: string = "";

  constructor(private gameApiService: GameApiService, private confirmationService: ConfirmationService) {
  }

  ngOnInit() {
    this.getGames()
  }

  getGames() {
    //get all games via gameApiService as Observable and set gameCollection to result
    this.gameApiService.getAllGames().subscribe(result => {
      this.gameCollection = result;
    });
  }

  deleteGame(game: GameModel | undefined) {
    //open dialog to confirm or cancel game-deletion
    this.confirmationService.confirm({
      dismissableMask: true,
      message: `Soll das Spiel ${game?.title} wirklich gelöscht werden?`,
      header: "Spiel löschen",
      acceptIcon: "none",
      acceptLabel: "Löschen",
      acceptButtonStyleClass: "p-button-danger",
      rejectIcon: "none",
      rejectLabel: "Abbrechen",
      closeOnEscape: false,
      defaultFocus: "reject",
      accept: () => {
        //on confirm, delete game via gameApiService
        this.gameApiService.deleteGame(game?.id).subscribe({
          next: () => {
            this.getGames();
          },
        })
      },
    })
  }

  filterGames(): GameModel[] {
    //filter gameCollection for games where either genre, any tag or the name fit entered searchTerm
    return this.gameCollection.filter(game =>
      game.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      (game.genre.label.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
      game.tags.some(tag => tag.label.toLowerCase().includes(this.searchTerm.toLowerCase()))
    );
  }
}
