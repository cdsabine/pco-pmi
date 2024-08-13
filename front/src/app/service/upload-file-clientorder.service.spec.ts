import { TestBed } from '@angular/core/testing';

import { UploadFileClientorderService } from './upload-file-clientorder.service';

describe('UploadFileClientorderService', () => {
  let service: UploadFileClientorderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UploadFileClientorderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
