import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBoxDialogueWindowComponent } from './view-box-dialogue-window.component';

describe('ViewCountryDialogueWindowComponent', () => {
  let component: ViewBoxDialogueWindowComponent;
  let fixture: ComponentFixture<ViewBoxDialogueWindowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewBoxDialogueWindowComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewBoxDialogueWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
