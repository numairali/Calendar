import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GivenData } from 'app/shared/model/given-data.model';
import { GivenDataService } from './given-data.service';
import { GivenDataComponent } from './given-data.component';
import { GivenDataDetailComponent } from './given-data-detail.component';
import { GivenDataUpdateComponent } from './given-data-update.component';
import { GivenDataDeletePopupComponent } from './given-data-delete-dialog.component';
import { IGivenData } from 'app/shared/model/given-data.model';

@Injectable({ providedIn: 'root' })
export class GivenDataResolve implements Resolve<IGivenData> {
  constructor(private service: GivenDataService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGivenData> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GivenData>) => response.ok),
        map((givenData: HttpResponse<GivenData>) => givenData.body)
      );
    }
    return of(new GivenData());
  }
}

export const givenDataRoute: Routes = [
  {
    path: '',
    component: GivenDataComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'GivenData'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GivenDataDetailComponent,
    resolve: {
      givenData: GivenDataResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GivenData'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GivenDataUpdateComponent,
    resolve: {
      givenData: GivenDataResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GivenData'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GivenDataUpdateComponent,
    resolve: {
      givenData: GivenDataResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GivenData'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const givenDataPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GivenDataDeletePopupComponent,
    resolve: {
      givenData: GivenDataResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GivenData'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
