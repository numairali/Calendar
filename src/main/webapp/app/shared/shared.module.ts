import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ShyftBaseSharedLibsModule, ShyftBaseSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [ShyftBaseSharedLibsModule, ShyftBaseSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [ShyftBaseSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ShyftBaseSharedModule {
  static forRoot() {
    return {
      ngModule: ShyftBaseSharedModule
    };
  }
}
