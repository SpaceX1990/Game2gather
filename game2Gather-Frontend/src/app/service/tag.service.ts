import { Injectable } from '@angular/core';
import {TagModel} from "../models/tag.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<TagModel[]>{
    return this.http.get<TagModel[]>("api/tag");
  }
}
