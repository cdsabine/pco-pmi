import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {ShellComponent} from "./shell/shell.component";
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import {SalesDashboardComponent} from "./sales-dashboard-component/sales-dashboard.component";
import {BrowserModule} from "@angular/platform-browser";
import {UploadCsvComponent} from "./upload-csv/upload-csv.component";
import {DownloadDatabaseComponent} from "./download-database/download-database.component";
import {ViewDatabaseComponent} from "./view-database/view-database.component";

@NgModule({
  declarations: [
    AppComponent,
    ShellComponent,
    SalesDashboardComponent,
    UploadCsvComponent,
    DownloadDatabaseComponent,
    ViewDatabaseComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
