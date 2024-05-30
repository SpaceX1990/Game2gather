import { Injectable } from '@angular/core';
import {TagModel} from "../models/tag.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TagService {
  //service to send http-requests to the backend and access stored or create new Tag-data

  constructor(private http: HttpClient) { }

  getAll(): Observable<TagModel[]>{
    return this.http.get<TagModel[]>("api/tag");
  }
}
