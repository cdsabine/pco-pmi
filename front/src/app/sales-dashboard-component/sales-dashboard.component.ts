import {Component, OnInit} from '@angular/core';
import {ClientorderService} from "../service/clientorder.service";
import { Chart, ChartOptions, ChartType, ChartDataset, CategoryScale, LinearScale, LineController, PieController, PointElement, LineElement, Title, Tooltip, Legend, BarController, BarElement, ArcElement} from 'chart.js';
import {ClientService} from "../service/client.service";
import {ProductServiceService} from "../service/product-service.service";
import {TeamServiceService} from "../service/team-service.service";
import {response} from "express";
import {Team} from "../model/team";

Chart.register(CategoryScale, LinearScale, LineController, PieController, PointElement, LineElement, Title, Tooltip, Legend, BarController, BarElement, ArcElement);

@Component({
  selector: 'app-sales-dashboard',
  templateUrl: './sales-dashboard.component.html',
  styleUrl: './sales-dashboard.component.css'
})
export class SalesDashboardComponent implements OnInit {
  public data: Map<string, Map<string, number>> = new Map();

  constructor(private clientOrderService: ClientorderService, private clientService: ClientService, private productService: ProductServiceService, private teamService: TeamServiceService) {}

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
  public chart1Data: ChartDataset[] = [{ data: [], label: 'Total Order Value',borderColor: 'rgba(75, 192, 192, 1)', borderWidth: 2, fill: false}];

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
  public chart2Data: ChartDataset[] = [{ data: [], label: 'Number of Orders',backgroundColor: 'rgba(54, 162, 235, 0.6)', borderColor: 'rgba(54, 162, 235, 1)', borderWidth: 2}];

  public chart3Options: ChartOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      tooltip: {
        callbacks: {
          label: function(context) {
            const label = context.label || '';
            const value = Number(context.raw) || 0;
            return `${label}: $${value.toFixed(2)}`;
          }
        }
      }
    }
  };
  public chart3Labels: string[] = [];
  public chart3Type: ChartType = 'pie';
  public chart3Legend = true;
  public chart3Data: ChartDataset<'pie'>[] = [
    {
      data: [],
      label: 'Product Values',
      backgroundColor: [
        '#1f77b4', // Blue
        '#ff7f0e', // Orange
        '#2ca02c', // Green
        '#d62728', // Red
        '#9467bd', // Purple
        '#8c564b', // Brown
        '#e377c2', // Pink
        '#7f7f7f', // Gray
        '#bcbd22', // Olive
        '#17becf'  // Teal
      ]
    }
  ];

  public chart4Options: ChartOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      tooltip: {
        callbacks: {
          label: function(context) {
            const label = context.label || '';
            const value = Number(context.raw) || 0;
            return `${label}: $${value.toFixed(2)}`;
          }
        }
      }
    }
  };
  public chart4Labels: string[] = [];
  public chart4Type: ChartType = 'pie';
  public chart4Legend = true;
  public chart4Data: ChartDataset<'pie'>[] = [
    {
      data: [],
      label: 'Product Values',
      backgroundColor: [
        '#1f77b4', // Blue
        '#ff7f0e', // Orange
        '#2ca02c', // Green
        '#d62728', // Red
        '#9467bd', // Purple
        '#8c564b', // Brown
        '#e377c2', // Pink
        '#7f7f7f', // Gray
        '#bcbd22', // Olive
        '#17becf'  // Teal
      ]
    }
  ];

  public chart5Options: ChartOptions = {
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
          text: 'Total Orders Made'
        }
      }
    }
  };
  public chart5Labels: string[] = [];
  public chart5Type: ChartType = 'line';
  public chart5Legend = true;
  public chart5Data: ChartDataset[] = [{ data: [], label: 'Total Orders Made',borderColor: 'rgba(75, 192, 192, 1)', borderWidth: 2, fill: false}];

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
    this.productService.findTotalValue().subscribe(data => {
      this.chart3Labels = Object.keys(data);
      this.chart3Data[0].data = Object.values(data);
    });
    this.teamService.findProductsFromTeam().subscribe(data => {
      this.teamService.findProductsFromTeam().subscribe(response => {
        this.processData(response);
      });
    });
    this.teamService.findTeamTotalValue().subscribe(data => {
      this.chart4Labels = Object.keys(data);
      this.chart4Data[0].data = Object.values(data);
    });
    this.clientOrderService.findAllOrdered().subscribe(data=>{
      const aggregatedData = this.aggregateOrdersByDate(data);
      this.chart5Labels = Object.keys(aggregatedData);
      this.chart5Data[0].data = Object.values(aggregatedData);
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
  private aggregateOrdersByDate(data: any[]): { [date: string]: number } {
    return data.reduce((acc, current) => {
      const date = current.dateOfOrder;
      if (acc[date]) {
        acc[date] += 1;
      } else {
        acc[date] = 1;
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
  private processData(response: any){
    this.data = new Map(Object.entries(response).map(([team, products]) => {
      return [team, new Map(Object.entries(products))];
    }));
  }
  getTeamNames(): string[] {
    return Array.from((this.data.keys()));
  }
  getProductNamesForTeam(team: string): string[] {
    return Array.from(this.data.get(team)?.keys() || []);
  }
}
