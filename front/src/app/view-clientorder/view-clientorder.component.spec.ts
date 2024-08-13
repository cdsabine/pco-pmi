import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewClientorderComponent } from './view-clientorder.component';

describe('ViewClientorderComponent', () => {
  let component: ViewClientorderComponent;
  let fixture: ComponentFixture<ViewClientorderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewClientorderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewClientorderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
