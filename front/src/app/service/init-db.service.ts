import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";

@Injectable()
export class InitDbService {
  private initBrandsURL: string;
  private initTeamsURL: string;
  constructor(private http: HttpClient) {
    this.initBrandsURL = 'http://localhost:8080/brand/initaliseBrands';
    this.initTeamsURL = 'http://localhost:8080/team/initaliseTeams';
  }
  public initBrand(): Observable<any> {
    return this.http.post(this.initBrandsURL, {}, {responseType: 'text'})
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return throwError(error);
        })
      );
  }
  public initTeam(): Observable<any> {
    return this.http.post(this.initTeamsURL, {}, {responseType: 'text'})
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return throwError(error);
        })
      );
  }
}
