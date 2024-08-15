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
  filteredBrand: any[] = [];
  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';
  selectedNationality: string = '';

  toggleSearch: boolean = false;
  constructor(private brandService: BrandServiceService) {
  }
  ngOnInit() {
    this.brandService.findAll().subscribe(data => {
      this.brand = data;
      this.filteredBrand = data;
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
  filterBrand(): void {
    let filtered = this.brand.filter(brand => {
      return (!this.selectedNationality || brand.nationality === this.selectedNationality);
    });
    this.filteredBrand = filtered;
  }
  clearFilters(): void {
    this.selectedNationality = '';
    this.filteredBrand = [...this.brand];
  }
}
