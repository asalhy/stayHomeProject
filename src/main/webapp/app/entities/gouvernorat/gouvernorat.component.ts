import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IGouvernorat } from 'app/shared/model/gouvernorat.model';
import { GouvernoratService } from './gouvernorat.service';

@Component({
  selector: 'jhi-gouvernorat',
  templateUrl: './gouvernorat.component.html'
})
export class GouvernoratComponent implements OnInit, OnDestroy {
  gouvernorats?: IGouvernorat[];
  eventSubscriber?: Subscription;

  constructor(protected gouvernoratService: GouvernoratService, protected eventManager: JhiEventManager) {}

  loadAll(): void {
    this.gouvernoratService.query().subscribe((res: HttpResponse<IGouvernorat[]>) => (this.gouvernorats = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGouvernorats();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGouvernorat): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGouvernorats(): void {
    this.eventSubscriber = this.eventManager.subscribe('gouvernoratListModification', () => this.loadAll());
  }
}
