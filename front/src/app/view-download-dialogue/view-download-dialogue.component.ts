import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {ClientorderService} from "../service/clientorder.service";
import {BoxServiceService} from "../service/box-service.service";
import {ProductServiceService} from "../service/product-service.service";
import {TeamServiceService} from "../service/team-service.service";

@Component({
  selector: 'app-view-download-dialogue',
  templateUrl: './view-download-dialogue.component.html',
  styleUrl: './view-download-dialogue.component.css'
})
export class ViewDownloadDialogueComponent {
  clientOrders: any[] = [];
  allBoxes: any[] = [];
  allProducts: any[] = [];
  allTeams: any[] = [];
  selectedOrders: any[] = [];
  selectedBox: any[] = [];
  selectedTeam: any[] = [];
  selectedProducts: any[] = [];
  constructor(@Inject(MAT_DIALOG_DATA) public data: { action: string }, private http: HttpClient, private dialogRef: MatDialogRef<ViewDownloadDialogueComponent>, private clientOrderService: ClientorderService,
  private boxService: BoxServiceService, private productService: ProductServiceService, private teamService: TeamServiceService) {}

  ngOnInit(): void {
    this.clientOrderService.findAllOrdered().subscribe(orders => {
      this.clientOrders = orders;
    });
    this.boxService.findAll().subscribe(box =>{
      this.allBoxes = box;
    });
    this.productService.findAll().subscribe(products => {
      this.allProducts = products;
    })
    this.teamService.findAll().subscribe(teams => {
      this.allTeams = teams;
    })
  }
  onDownloadCO(){
    this.clientOrderService.downloadCOasCSV(this.selectedOrders);
    this.dialogRef.close();
  }
  onDownloadBox(){
    this.boxService.downloadBoxAsCSV(this.selectedBox);
    this.dialogRef.close();
  }
  onDownloadAllProducts(){
    this.productService.downloadProductAsCSV(this.selectedProducts);
    this.dialogRef.close();
  }
  onDownloadTeam(){
    this.teamService.downloadTeamAsCSV(this.selectedTeam);
    this.dialogRef.close();
  }

  selectAll(): void {
    this.selectedProducts = [...this.allProducts];
  }
  deselectAll(): void {
    this.selectedProducts = [];
  }
}
