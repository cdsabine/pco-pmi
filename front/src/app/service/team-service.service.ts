import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Team} from "../model/team";

@Injectable()
export class TeamServiceService {
  private teamUrl: string;
  private teamProductUrl: string;
  private teamTotalValueUrl: string;

  constructor(private http: HttpClient) {
    this.teamUrl = 'http://localhost:8080/team/all';
    this.teamProductUrl = 'http://localhost:8080/team/allProductsTeamCount';
    this.teamTotalValueUrl = 'http://localhost:8080/team/allProductsTeamValue';
  }

  public findAll(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamUrl);
  }
  public findProductsFromTeam(): Observable<any>{
    return this.http.get<any>(this.teamProductUrl);
  }
  public findTeamTotalValue(): Observable<{[teamName: string]: number}>{
    return this.http.get<{[teamName: string]: number}>(this.teamTotalValueUrl);
  }
  downloadTeamAsCSV(selectedTeams: any[]): void{
    const csvData = this.convertToCsv(selectedTeams);
    const blob = new Blob([csvData], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'team-products.csv';
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
