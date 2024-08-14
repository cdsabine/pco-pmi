import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "../model/client";

@Injectable()
export class ClientService {
  private clientUrl: string;

  constructor(private http: HttpClient) {
    this.clientUrl = 'http://localhost:8080/user/client/all';
  }

  public findAll(): Observable<Client[]> {
    return this.http.get<Client[]>(this.clientUrl);
  }
}
