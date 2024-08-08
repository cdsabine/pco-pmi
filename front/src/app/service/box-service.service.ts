import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Box} from "../model/box";
import {Product} from "../model/product";

@Injectable()
export class BoxServiceService {
  private boxUrl: string;
  private productsInBoxUrl: string;
  constructor(private http: HttpClient) {
    this.boxUrl = 'http://localhost:8080/box/all';
    this.productsInBoxUrl = 'http://localhost:8080/box/allFromBox?boxNumber=';
  }

  public findAll(): Observable<Box[]> {
    return this.http.get<Box[]>(this.boxUrl);
  }
  public findAllProductsInBox(boxNumber: string): Observable<Box[]> {
    return this.http.get<Box[]>(this.productsInBoxUrl+boxNumber);
  }
}
