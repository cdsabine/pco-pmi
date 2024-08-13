import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Clientorder} from "../model/clientorder";
import {ClientorderService} from "../service/clientorder.service";
import {MatDialog} from "@angular/material/dialog";
import {ViewCoDialogueWindowComponent} from "../view-co-dialogue-window/view-co-dialogue-window.component";

@Component({
  selector: 'app-view-clientorder',
  templateUrl: './view-clientorder.component.html',
  styleUrl: './view-clientorder.component.css'
})
export class ViewClientorderComponent implements OnInit{
  clientorder: Clientorder[];
  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';

  toggleSearch: boolean = false;
  constructor(private clientOrderService: ClientorderService, public dialog: MatDialog) {
  }
  ngOnInit() {
    this.clientOrderService.findAll().subscribe(data => {
      this.clientorder = data;
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
  openProductList(clientorder: Clientorder): void {
    this.dialog.open(ViewCoDialogueWindowComponent, {
      width: '90vw',
      maxWidth: '1500px',
      height: 'auto',
      maxHeight: '90vw',
      data:  clientorder.productList
    });
  }
}
