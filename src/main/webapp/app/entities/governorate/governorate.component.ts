import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IGovernorate } from 'app/shared/model/governorate.model';
import { GovernorateService } from './governorate.service';

@Component({
  selector: 'jhi-governorate',
  templateUrl: './governorate.component.html'
})
export class GovernorateComponent implements OnInit, OnDestroy {
  governorates?: IGovernorate[];
  eventSubscriber?: Subscription;

  constructor(protected governorateService: GovernorateService, protected eventManager: JhiEventManager) {}

  loadAll(): void {
    this.governorateService.query().subscribe((res: HttpResponse<IGovernorate[]>) => (this.governorates = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGovernorates();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGovernorate): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGovernorates(): void {
    this.eventSubscriber = this.eventManager.subscribe('governorateListModification', () => this.loadAll());
  }
}
