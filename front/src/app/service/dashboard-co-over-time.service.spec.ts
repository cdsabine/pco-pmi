import { TestBed } from '@angular/core/testing';

import { DashboardCOOverTimeService } from './dashboard-co-over-time.service';

describe('DashboardCOOverTimeService', () => {
  let service: DashboardCOOverTimeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DashboardCOOverTimeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
