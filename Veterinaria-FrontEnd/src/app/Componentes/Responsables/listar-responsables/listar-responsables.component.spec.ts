import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarResponsablesComponent } from './listar-responsables.component';

describe('ListarResponsablesComponent', () => {
  let component: ListarResponsablesComponent;
  let fixture: ComponentFixture<ListarResponsablesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarResponsablesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarResponsablesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
