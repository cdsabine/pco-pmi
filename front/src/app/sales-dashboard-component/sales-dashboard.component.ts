import {Component, OnInit} from '@angular/core';
import {ClientorderService} from "../service/clientorder.service";
import { Chart, ChartOptions, ChartType, ChartDataset, CategoryScale, LinearScale, LineController, PieController, PointElement, LineElement, Title, Tooltip, Legend, BarController, BarElement, ArcElement} from 'chart.js';
import {ClientService} from "../service/client.service";
import {ProductServiceService} from "../service/product-service.service";

Chart.register(CategoryScale, LinearScale, LineController, PieController, PointElement, LineElement, Title, Tooltip, Legend, BarController, BarElement, ArcElement);

@Component({
  selector: 'app-sales-dashboard',
  templateUrl: './sales-dashboard.component.html',
  styleUrl: './sales-dashboard.component.css'
})
export class SalesDashboardComponent implements OnInit {

  constructor(private clientOrderService: ClientorderService, private clientService: ClientService, private productService: ProductServiceService) {}

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
