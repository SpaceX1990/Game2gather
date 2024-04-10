import { Injectable } from '@angular/core';
import {Genre} from "../models/genre.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Genre[]>{
    return this.http.get<Genre[]>("api/genre"); // pobieranie z backendu do frontendu
  }

}
