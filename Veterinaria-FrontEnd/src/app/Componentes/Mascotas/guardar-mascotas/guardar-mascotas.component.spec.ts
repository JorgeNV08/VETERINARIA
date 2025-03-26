import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuardarMascotasComponent } from './guardar-mascotas.component';

describe('GuardarMascotasComponent', () => {
  let component: GuardarMascotasComponent;
  let fixture: ComponentFixture<GuardarMascotasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GuardarMascotasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuardarMascotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
