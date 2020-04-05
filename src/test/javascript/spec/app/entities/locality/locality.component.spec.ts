import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StayHomeTestModule } from '../../../test.module';
import { LocalityComponent } from 'app/entities/locality/locality.component';
import { LocalityService } from 'app/entities/locality/locality.service';
import { Locality } from 'app/shared/model/locality.model';

describe('Component Tests', () => {
  describe('Locality Management Component', () => {
    let comp: LocalityComponent;
    let fixture: ComponentFixture<LocalityComponent>;
    let service: LocalityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [LocalityComponent]
      })
        .overrideTemplate(LocalityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocalityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocalityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Locality(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.localities && comp.localities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
