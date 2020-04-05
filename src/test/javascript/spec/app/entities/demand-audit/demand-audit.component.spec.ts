import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StayHomeTestModule } from '../../../test.module';
import { DemandAuditComponent } from 'app/entities/demand-audit/demand-audit.component';
import { DemandAuditService } from 'app/entities/demand-audit/demand-audit.service';
import { DemandAudit } from 'app/shared/model/demand-audit.model';

describe('Component Tests', () => {
  describe('DemandAudit Management Component', () => {
    let comp: DemandAuditComponent;
    let fixture: ComponentFixture<DemandAuditComponent>;
    let service: DemandAuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [DemandAuditComponent]
      })
        .overrideTemplate(DemandAuditComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DemandAuditComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DemandAuditService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DemandAudit(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.demandAudits && comp.demandAudits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
