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
  filteredUsers: any[] = [];

  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';
  selectedUser: string = '';
  selectedAddress: string = '';
  selectedCountry: string = '';

  toggleSearch: boolean = false;
  constructor(private appuserService: AppuserServiceService) {}
  ngOnInit() {
    this.appuserService.findAll().subscribe(data => {
      this.appuser = data;
      this.filteredUsers = data;
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
  filterUser(): void {
    let filtered = this.appuser.filter(appuser => {
      return (!this.selectedUser || appuser.userCode.toString() === this.selectedUser) &&
        (!this.selectedAddress || appuser.address === this.selectedAddress) &&
        (!this.selectedCountry || appuser.country.countryName === this.selectedCountry);
    });
    this.filteredUsers = filtered;
  }
  clearFilters(): void {
    this.selectedUser = '';
    this.selectedAddress = '';
    this.selectedCountry = '';
    this.filteredUsers = [...this.appuser];
  }
}
