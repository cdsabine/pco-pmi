import { Component } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ViewUploadCsvDialogueComponent} from "../view-upload-csv-dialogue/view-upload-csv-dialogue.component";

@Component({
  selector: 'app-upload-csv',
  templateUrl: './upload-csv.component.html',
  styleUrl: './upload-csv.component.css'
})
export class UploadCsvComponent {
  constructor(public dialog: MatDialog) {}

  openDialog(action: string): void {
    this.dialog.open(ViewUploadCsvDialogueComponent, {
      data: { action }
    });
  }
}
