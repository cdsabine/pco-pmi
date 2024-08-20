import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product";

@Injectable()
export class ProductServiceService {
  private productUrl: string;
  private productTotalValueUrl: string;
  constructor(private http: HttpClient) {
    this.productUrl = 'http://localhost:8080/product/all';
    this.productTotalValueUrl = 'http://localhost:8080/product/totalProductValue';
  }

  public findAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl);
  }
  public findTotalValue(): Observable<{[productName: string]: number}>{
    return this.http.get<{[productName: string]: number}>(this.productTotalValueUrl);
  }
  downloadProductAsCSV(products: any[]): void{
    const csvData = this.convertToCsv(products);
    const blob = new Blob([csvData], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'products.csv';
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
