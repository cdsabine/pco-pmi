import {ShellComponent} from "./shell/shell.component";
import {RouterModule, Routes} from "@angular/router";
import {SalesDashboardComponent} from "./sales-dashboard-component/sales-dashboard.component";
import {NgModule} from "@angular/core";
import {UploadCsvComponent} from "./upload-csv/upload-csv.component";
import {DownloadDatabaseComponent} from "./download-database/download-database.component";
import {ViewDatabaseComponent} from "./view-database/view-database.component";
import {ViewAppuserComponent} from "./view-appuser/view-appuser.component";

const routes: Routes = [
  {
    path: '', component: ShellComponent,
    children: [
      {
        path: '', component: SalesDashboardComponent,
      },
      {
        path: 'csv', component: UploadCsvComponent,
      },
      {
        path: 'download-db', component: DownloadDatabaseComponent,
      },
      {
        path: 'view-db',
        children: [
          {
            path: 'appuser', component: ViewAppuserComponent,
          },
          {
            path: '', component: ViewDatabaseComponent,
          }]
      }]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
