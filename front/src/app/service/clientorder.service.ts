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

  downloadCOasCSV(selectedOrders: any[]): void{
    const csvData = this.convertToCsv(selectedOrders);
    const blob = new Blob([csvData], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'client-orders.csv';
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
