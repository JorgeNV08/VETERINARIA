import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarVeterinariasComponent } from './listar-veterinarias.component';

describe('ListarVeterinariasComponent', () => {
  let component: ListarVeterinariasComponent;
  let fixture: ComponentFixture<ListarVeterinariasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarVeterinariasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarVeterinariasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
