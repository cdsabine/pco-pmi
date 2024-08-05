import { Pipe, PipeTransform } from '@angular/core';
import {Product} from "../model/product";

@Pipe({
  name: 'filterPipe',
})
export class FilterPipePipe implements PipeTransform {

  transform(product: Product[], searchText: string): any[] {
    if (!product) return [];
    if (!searchText) return product;
    searchText = searchText.toLowerCase();
    return product.filter(it => {
      return it.sku.toLowerCase().includes(searchText) || it.title.toLowerCase().includes(searchText);
    })
  }

}
