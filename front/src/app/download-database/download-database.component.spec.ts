import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DownloadDatabaseComponent } from './download-database.component';

describe('ShellComponent', () => {
  let component: DownloadDatabaseComponent;
  let fixture: ComponentFixture<DownloadDatabaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DownloadDatabaseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DownloadDatabaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
