import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { DemandAuditComponent } from './demand-audit.component';
import { DemandAuditDetailComponent } from './demand-audit-detail.component';
import { demandAuditRoute } from './demand-audit.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(demandAuditRoute)],
  declarations: [DemandAuditComponent, DemandAuditDetailComponent]
})
export class StayHomeDemandAuditModule {}
