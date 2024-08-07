import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Box} from "../model/box";
import {BoxServiceService} from "../service/box-service.service";

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
  constructor(private boxService: BoxServiceService) {
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
}
