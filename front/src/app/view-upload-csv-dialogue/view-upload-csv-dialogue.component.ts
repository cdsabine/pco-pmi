import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {UploadFileService} from "../service/upload-file.service";
import {response} from "express";
import {InitDbService} from "../service/init-db.service";
@Component({
  selector: 'app-view-upload-csv-dialogue',
  templateUrl: './view-upload-csv-dialogue.component.html',
  styleUrl: './view-upload-csv-dialogue.component.css'
})
export class ViewUploadCsvDialogueComponent {

  selectedFile: File | null = null;
  uploadProgress: number | null = null;
  uploadStatus: string | null = null;
  initStatusBrand: string | null = null;
  initStatusTeam: string | null = null;

  constructor(@Inject(MAT_DIALOG_DATA) public data: { action: string }, private http: HttpClient, private uploadFileService: UploadFileService, private initDbService: InitDbService) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    if (this.selectedFile) {
      this.uploadFileService.uploadFile(this.selectedFile).subscribe(
        (event: any) => {
          if (event.status === 'progress') {
            this.uploadProgress = event.message;
          } else if (event.status === 'success') {
            this.uploadStatus = 'Upload successful!';
            this.uploadProgress = null;
          }
        }
      );
    }
  }

  onInitaliseBrand(){
    this.initDbService.initBrand().subscribe(
      response => {
        this.initStatusBrand = response;
      }
    );
  }
  onInitaliseTeam(){
    this.initDbService.initTeam().subscribe(
      response => {
        this.initStatusTeam = response;
      }
    );
  }

  executeInit(){
    this.onInitaliseBrand();
    this.onInitaliseTeam();
  }

}
