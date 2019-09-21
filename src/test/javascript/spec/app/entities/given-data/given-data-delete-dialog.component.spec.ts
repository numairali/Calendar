/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShyftBaseTestModule } from '../../../test.module';
import { GivenDataDeleteDialogComponent } from 'app/entities/given-data/given-data-delete-dialog.component';
import { GivenDataService } from 'app/entities/given-data/given-data.service';

describe('Component Tests', () => {
  describe('GivenData Management Delete Component', () => {
    let comp: GivenDataDeleteDialogComponent;
    let fixture: ComponentFixture<GivenDataDeleteDialogComponent>;
    let service: GivenDataService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShyftBaseTestModule],
        declarations: [GivenDataDeleteDialogComponent]
      })
        .overrideTemplate(GivenDataDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GivenDataDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GivenDataService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
