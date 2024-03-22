import {Component} from '@angular/core';
import {GameModel} from "../models/game.model";

@Component({
  selector: 'app-game-collection',
  templateUrl: './game-collection.component.html',
  styleUrl: './game-collection.component.scss'
})
export class GameCollectionComponent {
  isSidebarVisible: boolean = false;

  gameCollection: GameModel[] = [
    {
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
    }
  ]

  deleteGame(id: number | undefined) {

  }
}
