import { Injectable } from '@angular/core';

@Injectable()
export class LogService {
  private logs: { action: string, timestamp: string }[] = [];
  private storageKey = 'buttonPressLogs';

  constructor() {
    const savedLogs = localStorage.getItem(this.storageKey);
    if (savedLogs) {
      this.logs = JSON.parse(savedLogs);
    }
  }

  addLog(action: string) {
    const timestamp = new Date().toLocaleString();
    this.logs.push({ action, timestamp });
    this.saveLogs();
  }

  getLogs() {
    return this.logs;
  }

  clearLogs() {
    this.logs = [];
    localStorage.removeItem(this.storageKey);
  }

  private saveLogs() {
    localStorage.setItem(this.storageKey, JSON.stringify(this.logs));
  }
}
