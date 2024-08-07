import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Team} from "../model/team";

@Injectable()
export class TeamServiceService {
  private teamUrl: string;

  constructor(private http: HttpClient) {
    this.teamUrl = 'http://localhost:8080/team/all';
  }

  public findAll(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamUrl);
  }
}
