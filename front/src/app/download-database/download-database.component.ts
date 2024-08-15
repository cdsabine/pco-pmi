import { Component } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {LogService} from "../service/log.service";
import {ViewDownloadDialogueComponent} from "../view-download-dialogue/view-download-dialogue.component";

@Component({
  selector: 'app-download-database',
  templateUrl: './download-database.component.html',
  styleUrl: './download-database.component.css'
})
export class DownloadDatabaseComponent {
  constructor(public dialog: MatDialog, private logService: LogService) {}

  openDialog(action: string): void {
    this.logService.addLog(action);
    this.dialog.open(ViewDownloadDialogueComponent, {
      width: '600px',  // Set maximum width
      maxHeight: '500px', // Set maximum height
      data: { action }
    });
  }
}
