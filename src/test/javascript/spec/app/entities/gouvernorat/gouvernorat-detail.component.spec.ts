import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StayHomeTestModule } from '../../../test.module';
import { GouvernoratDetailComponent } from 'app/entities/gouvernorat/gouvernorat-detail.component';
import { Gouvernorat } from 'app/shared/model/gouvernorat.model';

describe('Component Tests', () => {
  describe('Gouvernorat Management Detail Component', () => {
    let comp: GouvernoratDetailComponent;
    let fixture: ComponentFixture<GouvernoratDetailComponent>;
    const route = ({ data: of({ gouvernorat: new Gouvernorat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [GouvernoratDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GouvernoratDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GouvernoratDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gouvernorat on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gouvernorat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
