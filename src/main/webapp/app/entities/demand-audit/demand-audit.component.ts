import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IDemandAudit } from 'app/shared/model/demand-audit.model';
import { DemandAuditService } from './demand-audit.service';

@Component({
  selector: 'jhi-demand-audit',
  templateUrl: './demand-audit.component.html'
})
export class DemandAuditComponent implements OnInit, OnDestroy {
  demandAudits?: IDemandAudit[];
  eventSubscriber?: Subscription;

  constructor(protected demandAuditService: DemandAuditService, protected eventManager: JhiEventManager) {}

  loadAll(): void {
    this.demandAuditService.query().subscribe((res: HttpResponse<IDemandAudit[]>) => (this.demandAudits = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDemandAudits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDemandAudit): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDemandAudits(): void {
    this.eventSubscriber = this.eventManager.subscribe('demandAuditListModification', () => this.loadAll());
  }
}
