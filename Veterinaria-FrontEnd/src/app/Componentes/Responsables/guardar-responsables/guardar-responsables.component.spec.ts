import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuardarResponsablesComponent } from './guardar-responsables.component';

describe('GuardarResponsablesComponent', () => {
  let component: GuardarResponsablesComponent;
  let fixture: ComponentFixture<GuardarResponsablesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GuardarResponsablesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuardarResponsablesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
