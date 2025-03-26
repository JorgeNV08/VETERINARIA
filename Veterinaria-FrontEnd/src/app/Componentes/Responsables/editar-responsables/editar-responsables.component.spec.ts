import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarResponsablesComponent } from './editar-responsables.component';

describe('EditarResponsablesComponent', () => {
  let component: EditarResponsablesComponent;
  let fixture: ComponentFixture<EditarResponsablesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarResponsablesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarResponsablesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
