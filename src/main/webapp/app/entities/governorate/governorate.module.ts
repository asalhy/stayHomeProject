import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { GovernorateComponent } from './governorate.component';
import { GovernorateDetailComponent } from './governorate-detail.component';
import { governorateRoute } from './governorate.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(governorateRoute)],
  declarations: [GovernorateComponent, GovernorateDetailComponent]
})
export class StayHomeGovernorateModule {}
