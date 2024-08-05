import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAppuserComponent } from './view-appuser.component';

describe('ViewAppuserComponent', () => {
  let component: ViewAppuserComponent;
  let fixture: ComponentFixture<ViewAppuserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAppuserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAppuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
