import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDatabaseComponent } from './view-database.component';

describe('ShellComponent', () => {
  let component: ViewDatabaseComponent;
  let fixture: ComponentFixture<ViewDatabaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewDatabaseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewDatabaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
