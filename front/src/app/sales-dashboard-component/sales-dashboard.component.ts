import {Component, OnInit} from '@angular/core';
import {ClientorderService} from "../service/clientorder.service";
import { Chart, ChartOptions, ChartType, ChartDataset, CategoryScale, LinearScale, BarController, BarElement, LineController, PointElement, LineElement, Title, Tooltip, Legend  } from 'chart.js';
import {ClientService} from "../service/client.service";

Chart.register(CategoryScale, LinearScale, BarController, BarElement, LineController, PointElement, LineElement, Title, Tooltip, Legend);

@Component({
  selector: 'app-sales-dashboard',
  templateUrl: './sales-dashboard.component.html',
  styleUrl: './sales-dashboard.component.css'
})
export class SalesDashboardComponent implements OnInit {

  constructor(private clientOrderService: ClientorderService, private clientService: ClientService) {}

  public chart1Options: ChartOptions = {
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

  public chart1Labels: string[] = [];
  public chart1Type: ChartType = 'line';
  public chart1Legend = true;
  public chart1Data: ChartDataset[] = [{ data: [], label: 'Total Order Value' }];

  public chart2Options: ChartOptions = {
    responsive: true,
    scales: {
      x: {
        title: {
          display: true,
          text: 'Country'
        },
        type: 'category'
      },
      y: {
        title: {
          display: true,
          text: 'Number of Orders'
        }
      }
    }
  };

  public chart2Labels: string[] = [];
  public chart2Type: ChartType = 'bar';
  public chart2Legend = true;
  public chart2Data: ChartDataset[] = [{ data: [], label: 'Number of Orders' }];

  ngOnInit(): void {
    this.clientOrderService.findAllOrdered().subscribe(data=>{
      const aggregatedData = this.aggregateDataByDate(data);
      this.chart1Labels = Object.keys(aggregatedData);
      this.chart1Data[0].data = Object.values(aggregatedData);
    });
    this.clientService.findAll().subscribe(data => {
      const countryOrderCount = this.aggregateOrdersByCountry(data);
      this.chart2Labels = Object.keys(countryOrderCount);
      this.chart2Data[0].data = Object.values(countryOrderCount);
    });
  }

  private aggregateDataByDate(data: any[]): { [date: string]: number } {
    return data.reduce((acc, current) => {
      const date = current.dateOfOrder;
      const value = current.totalOrderValue;
      if (acc[date]) {
        acc[date] += value;
      } else {
        acc[date] = value;
      }
      return acc;
      }, {});
  }
  private aggregateOrdersByCountry(data: any[]): { [countryName: string]: number } {
    return data.reduce((acc, client) => {
      const countryName = client.country.countryName;
      const orderCount = client.clientOrderList.length;

      if (acc[countryName]) {
        acc[countryName] += orderCount;
      } else {
        acc[countryName] = orderCount;
      }

      return acc;
    }, {});
  }
}
