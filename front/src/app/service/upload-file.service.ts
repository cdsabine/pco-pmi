import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpEventType, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {Product} from "../model/product";

@Injectable()
export class UploadFileService {
  private uploadUrl: string;
  constructor(private http: HttpClient) {
    this.uploadUrl = 'http://localhost:8080/csvProduct/upload';
  }
  uploadFile(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post(this.uploadUrl, formData, {
      reportProgress: true,
      observe: 'events',
      responseType: 'text'
    }).pipe(
      map((event: any) => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            const progress = Math.round(100 * event.loaded / event.total);
            return { status: 'progress', message: progress };
          case HttpEventType.Response:
            return { status: 'success', message: event.body };
          default:
            return { status: 'unknown', message: event };
        }
      }),
      catchError((error: HttpErrorResponse) => {
        return throwError(error);
      })
    );
  }

}
