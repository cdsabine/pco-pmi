import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {UploadFileService} from "../service/upload-file.service";
import {InitDbService} from "../service/init-db.service";
import {UploadFileClientorderService} from "../service/upload-file-clientorder.service";
import {BoxMovementService} from "../service/box-movement.service";
import {AppuserServiceService} from "../service/appuser-service.service";
import {UploadFileAppuserService} from "../service/upload-file-appuser.service";
@Component({
  selector: 'app-view-upload-csv-dialogue',
  templateUrl: './view-upload-csv-dialogue.component.html',
  styleUrl: './view-upload-csv-dialogue.component.css'
})
export class ViewUploadCsvDialogueComponent {

  selectedFile: File | null = null;
  selectedFileClientOrder: File | null = null;
  selectedFileAppUser: File | null = null;
  uploadProgress: number | null = null;
  uploadStatus: string | null = null;
  initStatusBrand: string | null = null;
  initStatusTeam: string | null = null;
  sku: string = '';
  newBoxNumber: string = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data: { action: string }, private http: HttpClient, private dialogRef: MatDialogRef<ViewUploadCsvDialogueComponent>, private uploadFileService: UploadFileService,
              private uploadFileClientorderService: UploadFileClientorderService, private uploadFileAppUserService: UploadFileAppuserService, private boxMovementService: BoxMovementService, private initDbService: InitDbService) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
  onFileSelectedClientOrder(event: any) {
    this.selectedFileClientOrder = event.target.files[0];
  }
  onFileSelectedAppUser(event: any) {
    this.selectedFileAppUser = event.target.files[0];
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
  onUploadClientOrder(){
    if (this.selectedFileClientOrder) {
      this.uploadFileClientorderService.uploadFile(this.selectedFileClientOrder).subscribe(
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

  onUploadAppUser(){
    if (this.selectedFileAppUser) {
      this.uploadFileAppUserService.uploadFile(this.selectedFileAppUser).subscribe(
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
  onSubmit() {
    const currentDate = new Date().toISOString().split('T')[0];

    this.boxMovementService.moveProductBox(this.sku, this.newBoxNumber, currentDate).subscribe({
      next: (response) => {
        console.log('Product moved successfully', response);
        this.dialogRef.close();
      },
      error: (error) => {
        console.error('Error moving product', error);
      }
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }

}
