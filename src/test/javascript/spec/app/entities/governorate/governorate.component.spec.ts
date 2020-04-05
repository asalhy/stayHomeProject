import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StayHomeTestModule } from '../../../test.module';
import { GovernorateComponent } from 'app/entities/governorate/governorate.component';
import { GovernorateService } from 'app/entities/governorate/governorate.service';
import { Governorate } from 'app/shared/model/governorate.model';

describe('Component Tests', () => {
  describe('Governorate Management Component', () => {
    let comp: GovernorateComponent;
    let fixture: ComponentFixture<GovernorateComponent>;
    let service: GovernorateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [GovernorateComponent]
      })
        .overrideTemplate(GovernorateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GovernorateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GovernorateService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Governorate(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.governorates && comp.governorates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
