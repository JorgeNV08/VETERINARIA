import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuardarVeterinariaComponent } from './guardar-veterinaria.component';

describe('GuardarVeterinariaComponent', () => {
  let component: GuardarVeterinariaComponent;
  let fixture: ComponentFixture<GuardarVeterinariaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GuardarVeterinariaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuardarVeterinariaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
