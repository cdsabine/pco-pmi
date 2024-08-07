import { TestBed } from '@angular/core/testing';

import { BoxServiceService } from './box-service.service';

describe('BoxServiceService', () => {
  let service: BoxServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoxServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
