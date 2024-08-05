import { Component, OnInit } from '@angular/core';
import {app} from "../../../server";
import {Appuser} from "../model/appuser";
import {AppuserServiceService} from "../service/appuser-service.service";

@Component({
  selector: 'app-view-appuser',
  templateUrl: './view-appuser.component.html',
  styleUrl: './view-appuser.component.css'
})
export class ViewAppuserComponent implements OnInit {
  appuser: Appuser[];

  constructor(private appuserService: AppuserServiceService) {
  }

  ngOnInit() {
    this.appuserService.findAll().subscribe(data => {
      this.appuser = data;
    });
  }
}
