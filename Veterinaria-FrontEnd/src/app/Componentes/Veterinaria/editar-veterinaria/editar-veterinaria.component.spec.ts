import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarVeterinariaComponent } from './editar-veterinaria.component';

describe('EditarVeterinariaComponent', () => {
  let component: EditarVeterinariaComponent;
  let fixture: ComponentFixture<EditarVeterinariaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarVeterinariaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarVeterinariaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
