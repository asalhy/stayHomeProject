import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ILocality } from 'app/shared/model/locality.model';
import { LocalityService } from './locality.service';

@Component({
  selector: 'jhi-locality',
  templateUrl: './locality.component.html'
})
export class LocalityComponent implements OnInit, OnDestroy {
  localities?: ILocality[];
  eventSubscriber?: Subscription;

  constructor(protected localityService: LocalityService, protected eventManager: JhiEventManager) {}

  loadAll(): void {
    this.localityService.query().subscribe((res: HttpResponse<ILocality[]>) => (this.localities = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLocalities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILocality): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLocalities(): void {
    this.eventSubscriber = this.eventManager.subscribe('localityListModification', () => this.loadAll());
  }
}
