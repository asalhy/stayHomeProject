import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IMunicipality } from 'app/shared/model/municipality.model';
import { MunicipalityService } from './municipality.service';

@Component({
  selector: 'jhi-municipality',
  templateUrl: './municipality.component.html'
})
export class MunicipalityComponent implements OnInit, OnDestroy {
  municipalities?: IMunicipality[];
  eventSubscriber?: Subscription;

  constructor(protected municipalityService: MunicipalityService, protected eventManager: JhiEventManager) {}

  loadAll(): void {
    this.municipalityService.query().subscribe((res: HttpResponse<IMunicipality[]>) => (this.municipalities = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMunicipalities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMunicipality): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMunicipalities(): void {
    this.eventSubscriber = this.eventManager.subscribe('municipalityListModification', () => this.loadAll());
  }
}
