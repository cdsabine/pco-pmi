import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Brand} from "../model/brand";

@Injectable()
export class BrandServiceService {
  private brandUrl: string;

  constructor(private http: HttpClient) {
    this.brandUrl = 'http://localhost:8080/brand/all';
  }

  public findAll(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.brandUrl);
  }
}
