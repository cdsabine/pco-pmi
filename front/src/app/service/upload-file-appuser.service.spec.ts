import { TestBed } from '@angular/core/testing';

import { UploadFileAppuserService } from './upload-file-appuser.service';

describe('UploadFileAppuserService', () => {
  let service: UploadFileAppuserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UploadFileAppuserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
