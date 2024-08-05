import { TestBed } from '@angular/core/testing';

import { AppuserServiceService } from './appuser-service.service';

describe('AppuserServiceService', () => {
  let service: AppuserServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppuserServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
