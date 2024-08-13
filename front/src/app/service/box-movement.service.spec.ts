import { TestBed } from '@angular/core/testing';

import { BoxMovementService } from './box-movement.service';

describe('BoxMovementService', () => {
  let service: BoxMovementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoxMovementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
