import {Injectable} from '@angular/core';
import {TagModel} from "../models/tag.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) {
  }

  getAllTags(): Observable<TagModel[]> {
    return this.http.get<TagModel[]>("/api/tag");
  }
}
