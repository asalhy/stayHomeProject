import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StayHomeTestModule } from '../../../test.module';
import { MunicipalityComponent } from 'app/entities/municipality/municipality.component';
import { MunicipalityService } from 'app/entities/municipality/municipality.service';
import { Municipality } from 'app/shared/model/municipality.model';

describe('Component Tests', () => {
  describe('Municipality Management Component', () => {
    let comp: MunicipalityComponent;
    let fixture: ComponentFixture<MunicipalityComponent>;
    let service: MunicipalityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [MunicipalityComponent]
      })
        .overrideTemplate(MunicipalityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MunicipalityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MunicipalityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Municipality(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.municipalities && comp.municipalities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
