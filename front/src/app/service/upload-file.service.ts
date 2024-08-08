import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product";

@Injectable()
export class UploadFileService {
  private uploadUrl: string;
  constructor(private http: HttpClient) {
    this.uploadUrl = 'localhost:8080/csvProduct/upload';
  }

}
