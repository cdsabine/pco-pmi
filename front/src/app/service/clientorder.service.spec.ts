import { TestBed } from '@angular/core/testing';

import { ClientorderService } from './clientorder.service';

describe('ClientorderService', () => {
  let service: ClientorderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientorderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
