import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {app} from "../../../server";
import {Product} from "../model/product";
import {ProductServiceService} from "../service/product-service.service";

@Component({
  selector: 'app-view-database',
  templateUrl: './view-database.component.html',
  styleUrl: './view-database.component.css'
})
export class ViewDatabaseComponent implements OnInit{
  product: Product[];
  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';

  toggleSearch: boolean = false;
  constructor(private productService: ProductServiceService) {
  }
  ngOnInit() {
    this.productService.findAll().subscribe(data => {
      this.product = data;
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
