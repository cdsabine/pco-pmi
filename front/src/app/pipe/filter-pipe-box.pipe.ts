import { Pipe, PipeTransform } from '@angular/core';
import {Appuser} from "../model/appuser";
import {Box} from "../model/box";

@Pipe({
  name: 'filterPipeBox'
})
export class FilterPipeBoxPipe implements PipeTransform {

  transform(box: Box[], searchText: string): any[] {
    if (!box) return [];
    if (!searchText) return box;
    searchText = searchText.toLowerCase();
    return box.filter(it => {
      return it.boxNumber.toLowerCase().includes(searchText);
    })
  }

}
