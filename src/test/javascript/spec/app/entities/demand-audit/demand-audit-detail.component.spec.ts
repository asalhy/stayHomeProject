import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StayHomeTestModule } from '../../../test.module';
import { DemandAuditDetailComponent } from 'app/entities/demand-audit/demand-audit-detail.component';
import { DemandAudit } from 'app/shared/model/demand-audit.model';

describe('Component Tests', () => {
  describe('DemandAudit Management Detail Component', () => {
    let comp: DemandAuditDetailComponent;
    let fixture: ComponentFixture<DemandAuditDetailComponent>;
    const route = ({ data: of({ demandAudit: new DemandAudit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StayHomeTestModule],
        declarations: [DemandAuditDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DemandAuditDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DemandAuditDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load demandAudit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.demandAudit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
