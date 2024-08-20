import { Component } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ViewUploadCsvDialogueComponent} from "../view-upload-csv-dialogue/view-upload-csv-dialogue.component";
import {LogService} from "../service/log.service";

@Component({
  selector: 'app-upload-csv',
  templateUrl: './upload-csv.component.html',
  styleUrl: './upload-csv.component.css'
})
export class UploadCsvComponent {
  constructor(public dialog: MatDialog, private logService: LogService) {}

  openDialog(action: string): void {
    this.logService.addLog(action);
    this.dialog.open(ViewUploadCsvDialogueComponent, {
      data: { action },
      width: '30vw',
      maxWidth: '30vw',
      height: 'auto',
    });
  }
}
