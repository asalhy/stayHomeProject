import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDemandAudit } from 'app/shared/model/demand-audit.model';

@Component({
  selector: 'jhi-demand-audit-detail',
  templateUrl: './demand-audit-detail.component.html'
})
export class DemandAuditDetailComponent implements OnInit {
  demandAudit: IDemandAudit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demandAudit }) => (this.demandAudit = demandAudit));
  }

  previousState(): void {
    window.history.back();
  }
}
