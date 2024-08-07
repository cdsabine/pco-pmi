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
import {FilterPipeProductPipe} from "./pipe/filter-pipe-product.pipe";
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
import {CdkScrollable} from "@angular/cdk/overlay";
import {MatCard, MatCardContent} from "@angular/material/card";
import {FilterPipeAppuserPipe} from "./pipe/filter-pipe-appuser.pipe";
import {ViewBoxComponent} from "./view-box/view-box.component";
import {BoxServiceService} from "./service/box-service.service";
import {FilterPipeBoxPipe} from "./pipe/filter-pipe-box.pipe";
import {ViewBrandComponent} from "./view-brand/view-brand.component";
import {BrandServiceService} from "./service/brand-service.service";
import {FilterPipeBrandPipe} from "./pipe/filter-pipe-brand.pipe";
import {TeamServiceService} from "./service/team-service.service";
import {ViewTeamComponent} from "./view-team/view-team.component";
import {FilterPipeTeamPipe} from "./pipe/filter-pipe-team.pipe";
@NgModule({
  declarations: [
    AppComponent,
    ShellComponent,
    SalesDashboardComponent,
    UploadCsvComponent,
    DownloadDatabaseComponent,
    ViewDatabaseComponent,
    ViewAppuserComponent,
    ViewBoxComponent,
    ViewBrandComponent,
    ViewTeamComponent,
    FilterPipeProductPipe,
    FilterPipeAppuserPipe,
    FilterPipeBoxPipe,
    FilterPipeBrandPipe,
    FilterPipeTeamPipe
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
    MatLine,
    CdkScrollable,
    MatCard,
    MatCardContent
  ],
  providers: [ProductServiceService, AppuserServiceService, BoxServiceService, BrandServiceService, TeamServiceService, provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }
