import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {UploadFileService} from "../service/upload-file.service";
import {response} from "express";
@Component({
  selector: 'app-view-upload-csv-dialogue',
  templateUrl: './view-upload-csv-dialogue.component.html',
  styleUrl: './view-upload-csv-dialogue.component.css'
})
export class ViewUploadCsvDialogueComponent {
  selectedFile: File | null = null;
  constructor(@Inject(MAT_DIALOG_DATA) public data: { action: string }, private http: HttpClient, private uploadFileService: UploadFileService) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadFile(): void {

  }
}
