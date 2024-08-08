import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Box} from "../model/box";
import { MatDialog } from '@angular/material/dialog';
import {BoxServiceService} from "../service/box-service.service";
import {Product} from "../model/product";
import {ViewBoxDialogueWindowComponent} from "../view-box-dialogue-window/view-box-dialogue-window.component";

@Component({
  selector: 'app-view-box',
  templateUrl: './view-box.component.html',
  styleUrl: './view-box.component.css'
})
export class ViewBoxComponent implements OnInit{
  box: Box[];
  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';

  toggleSearch: boolean = false;
  constructor(private boxService: BoxServiceService, public dialog: MatDialog) {
  }
  ngOnInit() {
    this.boxService.findAll().subscribe(data => {
      this.box = data;
    });
  }
  openSearch() {
    this.toggleSearch = true;
    this.searchbar.nativeElement.focus();
  }
  searchClose() {
    this.searchText = '';
    this.toggleSearch = false;
  }

  openProductList(box: Box): void {
    this.dialog.open(ViewBoxDialogueWindowComponent, {
      width: '90vw',
      maxWidth: '1500px',
      height: 'auto',
      maxHeight: '90vw',
      data:  box.products
    });
  }
}
