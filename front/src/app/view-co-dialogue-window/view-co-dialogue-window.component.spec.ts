import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCoDialogueWindowComponent } from './view-co-dialogue-window.component';

describe('ViewCoDialogueWindowComponent', () => {
  let component: ViewCoDialogueWindowComponent;
  let fixture: ComponentFixture<ViewCoDialogueWindowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewCoDialogueWindowComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewCoDialogueWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
