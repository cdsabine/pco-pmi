import { Pipe, PipeTransform } from '@angular/core';
import {Team} from "../model/team";
import {Clientorder} from "../model/clientorder";

@Pipe({
  name: 'filterPipeClientorder'
})
export class FilterPipeClientorderPipe implements PipeTransform {

  transform(clientorder: Clientorder[], searchText: string): any[] {
    if (!clientorder) return [];
    if (!searchText) return clientorder;
    searchText = searchText.toLowerCase();
    return clientorder.filter(it => {
      return it.clientOrderCode.toString().toLowerCase().includes(searchText) || it.dateOfOrder.toString().toLowerCase().includes(searchText);
    })
  }

}
