import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Box} from "../model/box";
@Injectable()
export class BoxMovementService {
  private boxMovementUrl: string;
  constructor(private http: HttpClient) {
    this.boxMovementUrl = 'http://localhost:8080/pLocationChangeController/moveProductBox?SKU=';
  }

  public moveProductBox(SKU: string, newBoxNumber: string, currentDate: string): Observable<any> {
    return this.http.post(this.boxMovementUrl+SKU+'&newBoxNumber='+newBoxNumber+'&currentDate='+currentDate,{});
  }
}
