import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'game2Gather-Frontend';
  backendTestObjects?: TestEntity[];


  constructor(private http: HttpClient) {

    http.get<TestEntity[]>("/test").subscribe(value => {
        this.backendTestObjects = value;
      },
    )
  }


}

interface TestEntity {
  id: number,
  text: string
}
