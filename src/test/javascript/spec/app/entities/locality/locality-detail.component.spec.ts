import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StayHomeTestModule } from '../../../test.module';
import { LocalityDetailComponent } from 'app/entities/locality/locality-detail.component';
import { Locality } from 'app/shared/model/locality.model';

describe('Component Tests', () => {
  describe('Locality Management Detail Component', () => {
    let comp: LocalityDetailComponent;
    let fixture: ComponentFixture<LocalityDetailComponent>;
    const route = ({ data: of({ locality: new Locality(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [LocalityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LocalityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LocalityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load locality on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.locality).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
