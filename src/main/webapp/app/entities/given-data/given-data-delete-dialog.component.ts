import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGivenData } from 'app/shared/model/given-data.model';
import { GivenDataService } from './given-data.service';

@Component({
  selector: 'jhi-given-data-delete-dialog',
  templateUrl: './given-data-delete-dialog.component.html'
})
export class GivenDataDeleteDialogComponent {
  givenData: IGivenData;

  constructor(protected givenDataService: GivenDataService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.givenDataService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'givenDataListModification',
        content: 'Deleted an givenData'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-given-data-delete-popup',
  template: ''
})
export class GivenDataDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ givenData }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GivenDataDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.givenData = givenData;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/given-data', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/given-data', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
