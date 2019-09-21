import { NgModule } from '@angular/core';

import { ShyftBaseSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [ShyftBaseSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [ShyftBaseSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ShyftBaseSharedCommonModule {}
