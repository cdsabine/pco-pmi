import { Pipe, PipeTransform } from '@angular/core';
import {Brand} from "../model/brand";

@Pipe({
  name: 'filterPipeBrand'
})
export class FilterPipeBrandPipe implements PipeTransform {

  transform(brand: Brand[], searchText: string): any[] {
    if (!brand) return [];
    if (!searchText) return brand;
    searchText = searchText.toLowerCase();
    return brand.filter(it => {
      return it.brandName.toLowerCase().includes(searchText) || it.brandCode.toString().toLowerCase().includes(searchText) || it.nationality.toLowerCase().includes(searchText);
    })
  }

}
