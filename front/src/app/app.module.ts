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
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
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
import {ViewBoxDialogueWindowComponent} from "./view-box-dialogue-window/view-box-dialogue-window.component";
import {ViewUploadCsvDialogueComponent} from "./view-upload-csv-dialogue/view-upload-csv-dialogue.component";
import {UploadFileService} from "./service/upload-file.service";
import {InitDbService} from "./service/init-db.service";
import {LogService} from "./service/log.service";
import {LogHistoryComponent} from "./log-history/log-history.component";
import {ClientorderService} from "./service/clientorder.service";
import {FilterPipeClientorderPipe} from "./pipe/filter-pipe-clientorder.pipe";
import {ViewClientorderComponent} from "./view-clientorder/view-clientorder.component";
import {ViewCoDialogueWindowComponent} from "./view-co-dialogue-window/view-co-dialogue-window.component";
import {UploadFileClientorderService} from "./service/upload-file-clientorder.service";
import {BoxMovementService} from "./service/box-movement.service";
import {DashboardCOOverTimeService} from "./service/dashboard-co-over-time.service";
import {BaseChartDirective} from "ng2-charts";
import {ClientService} from "./service/client.service";
import {ViewDownloadDialogueComponent} from "./view-download-dialogue/view-download-dialogue.component";

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
    ViewClientorderComponent,
    ViewBoxDialogueWindowComponent,
    ViewCoDialogueWindowComponent,
    ViewUploadCsvDialogueComponent,
    ViewDownloadDialogueComponent,
    LogHistoryComponent,
    FilterPipeProductPipe,
    FilterPipeAppuserPipe,
    FilterPipeBoxPipe,
    FilterPipeBrandPipe,
    FilterPipeTeamPipe,
    FilterPipeClientorderPipe
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
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    BaseChartDirective
  ],
  providers: [ProductServiceService, AppuserServiceService, BoxServiceService, BrandServiceService, TeamServiceService,
    ClientorderService, UploadFileService, UploadFileClientorderService, BoxMovementService, DashboardCOOverTimeService, ClientService, InitDbService, LogService, provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }
