import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Clientorder} from "../model/clientorder";

@Injectable()
export class ClientorderService {
  private clientOrderUrl: string;
  private clientOrderUrlOrderedByDate: string;

  constructor(private http: HttpClient) {
    this.clientOrderUrl = 'http://localhost:8080/clientorder/all';
    this.clientOrderUrlOrderedByDate = 'http://localhost:8080/clientorder/allOrderedByDate';
  }

  public findAll(): Observable<Clientorder[]> {
    return this.http.get<Clientorder[]>(this.clientOrderUrl);
  }
  public findAllOrdered(): Observable<Clientorder[]> {
    return this.http.get<Clientorder[]>(this.clientOrderUrlOrderedByDate);
  }
}
