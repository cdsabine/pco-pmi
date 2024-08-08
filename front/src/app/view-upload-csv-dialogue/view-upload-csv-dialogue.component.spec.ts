import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUploadCsvDialogueComponent } from './view-upload-csv-dialogue.component';

describe('ViewUploadCsvDialogueComponent', () => {
  let component: ViewUploadCsvDialogueComponent;
  let fixture: ComponentFixture<ViewUploadCsvDialogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewUploadCsvDialogueComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewUploadCsvDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
