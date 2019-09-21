/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { ShyftBaseTestModule } from '../../../test.module';
import { GivenDataUpdateComponent } from 'app/entities/given-data/given-data-update.component';
import { GivenDataService } from 'app/entities/given-data/given-data.service';
import { GivenData } from 'app/shared/model/given-data.model';

describe('Component Tests', () => {
  describe('GivenData Management Update Component', () => {
    let comp: GivenDataUpdateComponent;
    let fixture: ComponentFixture<GivenDataUpdateComponent>;
    let service: GivenDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShyftBaseTestModule],
        declarations: [GivenDataUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GivenDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GivenDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GivenDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GivenData(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new GivenData();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
