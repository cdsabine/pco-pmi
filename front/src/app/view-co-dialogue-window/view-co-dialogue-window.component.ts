import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Product} from "../model/product";

@Component({
  selector: 'app-view-co-dialogue-window',
  templateUrl: './view-co-dialogue-window.component.html',
  styleUrl: './view-co-dialogue-window.component.css'
})
export class ViewCoDialogueWindowComponent {
  constructor(public dialogRef: MatDialogRef<ViewCoDialogueWindowComponent>, @Inject(MAT_DIALOG_DATA) public products: Product[]) {
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}
