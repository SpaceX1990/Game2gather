import { Injectable } from '@angular/core';
import {GenreModel} from "../models/genre.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<GenreModel[]>{
    return this.http.get<GenreModel[]>("api/genre");
  }

}
