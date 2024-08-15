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
  downloadBoxAsCSV(selectedBox: any[]): void{
    const csvData = this.convertToCsv(selectedBox);
    const blob = new Blob([csvData], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'box-products.csv';
    a.click();
    window.URL.revokeObjectURL(url);
  }
  private convertToCsv(data: any[]): string {
    const headers = Object.keys(data[0]);
    const csvRows = data.map(order =>
      headers.map(header => JSON.stringify(order[header], replacer)).join(',')
    );
    return [headers.join(','), ...csvRows].join('\r\n');
    function replacer(key: string, value: any) {
      return value === null ? '' : value;
    }
  }
}
