import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Appuser} from "../model/appuser";
import {AppuserServiceService} from "../service/appuser-service.service";

@Component({
  selector: 'app-view-appuser',
  templateUrl: './view-appuser.component.html',
  styleUrl: './view-appuser.component.css'
})
export class ViewAppuserComponent implements OnInit {
  appuser: Appuser[];
  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';

  toggleSearch: boolean = false;
  constructor(private appuserService: AppuserServiceService) {
  }
  ngOnInit() {
    this.appuserService.findAll().subscribe(data => {
      this.appuser = data;
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
