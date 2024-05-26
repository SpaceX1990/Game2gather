import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SessionModel} from "../models/session.model";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) {
  }

  getAllActiveSessios(): Observable<SessionModel[]> {
    return this.http.get<SessionModel[]>("/api/session/active");
  }

  getAllPastSessios(): Observable<SessionModel[]> {
    return this.http.get<SessionModel[]>("/api/session/past");
  }

  deleteSession(id: number | undefined): Observable<SessionModel> {
    return this.http.delete<SessionModel>('/api/session/delete/' + id);
  }

  saveSession(newSession: SessionModel) {
    return this.http.post<SessionModel>('/api/session', newSession)
  }

  getSessionById(id: string) {
    return this.http.get<SessionModel>('/api/session/' + id);
  }

  updateSession(sessionToUpdate: SessionModel) {
    return this.http.put<SessionModel>('/api/session/', sessionToUpdate);
  }
}
