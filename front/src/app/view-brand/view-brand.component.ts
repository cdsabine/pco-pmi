import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Brand} from "../model/brand";
import {BrandServiceService} from "../service/brand-service.service";

@Component({
  selector: 'app-view-brand',
  templateUrl: './view-brand.component.html',
  styleUrl: './view-brand.component.css'
})
export class ViewBrandComponent implements OnInit{
  brand: Brand[];
  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';

  toggleSearch: boolean = false;
  constructor(private brandService: BrandServiceService) {
  }
  ngOnInit() {
    this.brandService.findAll().subscribe(data => {
      this.brand = data;
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
