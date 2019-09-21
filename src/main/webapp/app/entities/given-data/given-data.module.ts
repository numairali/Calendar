import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShyftBaseSharedModule } from 'app/shared';
import {
  GivenDataComponent,
  GivenDataDetailComponent,
  GivenDataUpdateComponent,
  GivenDataDeletePopupComponent,
  GivenDataDeleteDialogComponent,
  givenDataRoute,
  givenDataPopupRoute
} from './';

const ENTITY_STATES = [...givenDataRoute, ...givenDataPopupRoute];

@NgModule({
  imports: [ShyftBaseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GivenDataComponent,
    GivenDataDetailComponent,
    GivenDataUpdateComponent,
    GivenDataDeleteDialogComponent,
    GivenDataDeletePopupComponent
  ],
  entryComponents: [GivenDataComponent, GivenDataUpdateComponent, GivenDataDeleteDialogComponent, GivenDataDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ShyftBaseGivenDataModule {}
