import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SessionModel} from "../models/SessionModel";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) {
  }

  getAllActiveSessios(): Observable<SessionModel[]> {
    return this.http.get<SessionModel[]>("/api/session/active");
  }

}
