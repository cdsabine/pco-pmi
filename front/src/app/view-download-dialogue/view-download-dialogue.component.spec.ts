import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDownloadDialogueComponent } from './view-download-dialogue.component';

describe('ViewDownloadDialogueComponent', () => {
  let component: ViewDownloadDialogueComponent;
  let fixture: ComponentFixture<ViewDownloadDialogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewDownloadDialogueComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewDownloadDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
