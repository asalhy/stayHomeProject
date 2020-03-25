import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { DelegationComponent } from './delegation.component';
import { DelegationDetailComponent } from './delegation-detail.component';
import { delegationRoute } from './delegation.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(delegationRoute)],
  declarations: [DelegationComponent, DelegationDetailComponent]
})
export class StayHomeDelegationModule {}
