import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Product} from "../model/product";
import {ProductServiceService} from "../service/product-service.service";

@Component({
  selector: 'app-view-database',
  templateUrl: './view-database.component.html',
  styleUrl: './view-database.component.css'
})
export class ViewDatabaseComponent implements OnInit{
  product: Product[];
  filteredProducts: any[] = [];
  @ViewChild('searchbar') searchbar: ElementRef;

  searchText = '';
  selectedColour: string = '';
  selectedProductCondition: string = '';
  selectedVendorCode: string = '';
  sortOrder: string = '';

  toggleSearch: boolean = false;
  constructor(private productService: ProductServiceService) {
  }
  ngOnInit() {
    this.productService.findAll().subscribe(data => {
      this.product = data;
      this.filteredProducts = data;
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
  filterProducts(): void {
    let filtered = this.product.filter(product => {
      return (!this.selectedColour || product.colour === this.selectedColour) &&
        (!this.selectedProductCondition || product.prodCondition === this.selectedProductCondition) &&
        (!this.selectedVendorCode || product.vendorCode === this.selectedVendorCode);
    });

    if (this.sortOrder === 'asc') {
      filtered.sort((a, b) => a.price - b.price);
    } else if (this.sortOrder === 'desc') {
      filtered.sort((a, b) => b.price - a.price);
    }
    this.filteredProducts = filtered;
  }
  clearFilters(): void {
    this.selectedColour = '';
    this.selectedProductCondition = '';
    this.selectedVendorCode = '';
    this.sortOrder = '';
    this.filteredProducts = [...this.product];
  }
  sortByPrice(order: string): void {
    this.sortOrder = order;
    this.filterProducts();
  }
}
