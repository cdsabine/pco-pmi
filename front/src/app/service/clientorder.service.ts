import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Clientorder} from "../model/clientorder";

@Injectable()
export class ClientorderService {
  private clientOrderUrl: string;

  constructor(private http: HttpClient) {
    this.clientOrderUrl = 'http://localhost:8080/clientorder/all';
  }

  public findAll(): Observable<Clientorder[]> {
    return this.http.get<Clientorder[]>(this.clientOrderUrl);
  }
}
