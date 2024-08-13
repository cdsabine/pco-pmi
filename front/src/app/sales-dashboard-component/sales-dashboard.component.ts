import {Component, OnInit} from '@angular/core';
import {ClientorderService} from "../service/clientorder.service";
import { Chart, ChartOptions, ChartType, ChartDataset, CategoryScale, LinearScale, LineController, PointElement, LineElement, Title, Tooltip, Legend  } from 'chart.js';

Chart.register(CategoryScale, LinearScale, LineController, PointElement, LineElement, Title, Tooltip, Legend);

@Component({
  selector: 'app-sales-dashboard',
  templateUrl: './sales-dashboard.component.html',
  styleUrl: './sales-dashboard.component.css'
})
export class SalesDashboardComponent implements OnInit {

  constructor(private clientOrderService: ClientorderService) {}

  public chartOptions: ChartOptions = {
    responsive: true,
    scales: {
      x: {
        title: {
          display: true,
          text: 'Date of Order'
        },
        type: 'category'
      },
      y: {
        title: {
          display: true,
          text: 'Total Order Value'
        }
      }
    }
  };

  public chartLabels: string[] = [];
  public chartType: ChartType = 'line';
  public chartLegend = true;
  public chartData: ChartDataset[] = [{ data: [], label: 'Total Order Value' }];

  ngOnInit(): void {
    this.clientOrderService.findAll().subscribe(data=>{
      this.chartLabels = data.map(order=> order.dateOfOrder);
      this.chartData[0].data = data.map(order => order.totalOrderValue);
    });
  }
}
