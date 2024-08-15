import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Team} from "../model/team";
import {TeamServiceService} from "../service/team-service.service";

@Component({
  selector: 'app-view-team',
  templateUrl: './view-team.component.html',
  styleUrl: './view-team.component.css'
})
export class ViewTeamComponent implements OnInit{
  team: Team[];
  filteredTeam: any[] = [];
  @ViewChild('searchbar') searchbar: ElementRef;
  searchText = '';
  selectedNationality: string = '';

  toggleSearch: boolean = false;
  constructor(private teamService: TeamServiceService) {
  }
  ngOnInit() {
    this.teamService.findAll().subscribe(data => {
      this.team = data;
      this.filteredTeam = data;
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
  filterTeam(): void {
    let filtered = this.team.filter(brand => {
      return (!this.selectedNationality || brand.nationality === this.selectedNationality);
    });
    this.filteredTeam = filtered;
  }
  clearFilters(): void {
    this.selectedNationality = '';
    this.filteredTeam = [...this.team];
  }
}
