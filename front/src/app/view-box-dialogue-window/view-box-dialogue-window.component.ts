import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Product} from "../model/product";

@Component({
  selector: 'app-view-box-dialogue-window',
  templateUrl: './view-box-dialogue-window.component.html',
  styleUrl: './view-box-dialogue-window.component.css'
})
export class ViewBoxDialogueWindowComponent{
  constructor(public dialogRef: MatDialogRef<ViewBoxDialogueWindowComponent>, @Inject(MAT_DIALOG_DATA) public products: Product[]) {
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
