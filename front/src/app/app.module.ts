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
import { HttpClientModule } from "@angular/common/http";
import {ProductServiceService} from "./service/product-service.service";
import {ViewAppuserComponent} from "./view-appuser/view-appuser.component";
import {AppuserServiceService} from "./service/appuser-service.service";
import {FilterPipePipe} from "./pipe/filter-pipe.pipe";
import {FormsModule} from "@angular/forms";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {MatLine} from "@angular/material/core";
@NgModule({
  declarations: [
    AppComponent,
    ShellComponent,
    SalesDashboardComponent,
    UploadCsvComponent,
    DownloadDatabaseComponent,
    ViewDatabaseComponent,
    ViewAppuserComponent,
    FilterPipePipe
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule,
    MatFormFieldModule,
    MatListModule,
    MatInputModule,
    MatLine
  ],
  providers: [ProductServiceService, AppuserServiceService, provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }
