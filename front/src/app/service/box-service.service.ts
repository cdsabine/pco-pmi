import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Box} from "../model/box";

@Injectable()
export class BoxServiceService {
  private boxUrl: string;

  constructor(private http: HttpClient) {
    this.boxUrl = 'http://localhost:8080/box/all';
  }

  public findAll(): Observable<Box[]> {
    return this.http.get<Box[]>(this.boxUrl);
  }
}
