import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeanseComponent } from './seanse.component';

describe('SeanseComponent', () => {
  let component: SeanseComponent;
  let fixture: ComponentFixture<SeanseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeanseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeanseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
