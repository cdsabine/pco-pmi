import { Pipe, PipeTransform } from '@angular/core';
import {Team} from "../model/team";

@Pipe({
  name: 'filterPipeTeam',
})
export class FilterPipeTeamPipe implements PipeTransform {

  transform(team: Team[], searchText: string): any[] {
    if (!team) return [];
    if (!searchText) return team;
    searchText = searchText.toLowerCase();
    return team.filter(it => {
      return it.teamCode.toString().toLowerCase().includes(searchText) || it.teamName.toLowerCase().includes(searchText) || it.nationality.toLowerCase().includes(searchText);
    })
  }

}
