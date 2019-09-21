/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShyftBaseTestModule } from '../../../test.module';
import { GivenDataDetailComponent } from 'app/entities/given-data/given-data-detail.component';
import { GivenData } from 'app/shared/model/given-data.model';

describe('Component Tests', () => {
  describe('GivenData Management Detail Component', () => {
    let comp: GivenDataDetailComponent;
    let fixture: ComponentFixture<GivenDataDetailComponent>;
    const route = ({ data: of({ givenData: new GivenData(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShyftBaseTestModule],
        declarations: [GivenDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GivenDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GivenDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.givenData).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
