import {Component, OnInit} from '@angular/core';
import {LogService} from "../service/log.service";

@Component({
  selector: 'app-log-history',
  templateUrl: './log-history.component.html',
  styleUrl: './log-history.component.css'
})
export class LogHistoryComponent implements OnInit{
  logs: { action: string, timestamp: string }[] = [];

  constructor(private logService: LogService) {}

  ngOnInit(): void {
    this.logs = this.logService.getLogs();
  }

  clearLog() {
    this.logService.clearLogs();
    this.logs = [];
  }
}
