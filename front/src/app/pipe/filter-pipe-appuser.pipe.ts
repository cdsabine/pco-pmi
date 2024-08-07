import { Pipe, PipeTransform } from '@angular/core';
import {Appuser} from "../model/appuser";

@Pipe({
  name: 'filterPipeAppuser',
})
export class FilterPipeAppuserPipe implements PipeTransform {

  transform(appusers: Appuser[], searchText: string): any[] {
    if (!appusers) return [];
    if (!searchText) return appusers;
    searchText = searchText.toLowerCase();
    return appusers.filter(it => {
      return it.userCode.toString().toLowerCase().includes(searchText) || it.appUsername.toLowerCase().includes(searchText);
    })
  }

}
