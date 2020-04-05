import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StayHomeTestModule } from '../../../test.module';
import { GouvernoratComponent } from 'app/entities/gouvernorat/gouvernorat.component';
import { GouvernoratService } from 'app/entities/gouvernorat/gouvernorat.service';
import { Gouvernorat } from 'app/shared/model/gouvernorat.model';

describe('Component Tests', () => {
  describe('Gouvernorat Management Component', () => {
    let comp: GouvernoratComponent;
    let fixture: ComponentFixture<GouvernoratComponent>;
    let service: GouvernoratService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [GouvernoratComponent]
      })
        .overrideTemplate(GouvernoratComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GouvernoratComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GouvernoratService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Gouvernorat(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.gouvernorats && comp.gouvernorats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
