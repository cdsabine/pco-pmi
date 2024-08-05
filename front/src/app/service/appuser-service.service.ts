import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appuser} from "../model/appuser";

@Injectable()
export class AppuserServiceService {
  private appuserUrl: string;

  constructor(private http: HttpClient) {
    this.appuserUrl = 'http://localhost:8080/user/all';
  }

  public findAll(): Observable<Appuser[]> {
    return this.http.get<Appuser[]>(this.appuserUrl);
  }
}
