import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IServiceType } from 'app/shared/model/service-type.model';
import { ServiceTypeService } from './service-type.service';

@Component({
  selector: 'jhi-service-type',
  templateUrl: './service-type.component.html'
})
export class ServiceTypeComponent implements OnInit, OnDestroy {
  serviceTypes?: IServiceType[];
  eventSubscriber?: Subscription;

  constructor(protected serviceTypeService: ServiceTypeService, protected eventManager: JhiEventManager) {}

  loadAll(): void {
    this.serviceTypeService.query().subscribe((res: HttpResponse<IServiceType[]>) => (this.serviceTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInServiceTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IServiceType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInServiceTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('serviceTypeListModification', () => this.loadAll());
  }
}
