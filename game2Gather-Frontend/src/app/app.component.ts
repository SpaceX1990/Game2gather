import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'game2Gather-Frontend';
  backendTestString?: string;


  constructor(private http: HttpClient) {

    http.get("/test",{responseType: "text"}).subscribe(value => {
        this.backendTestString = value;
      },
    )
  }


}
