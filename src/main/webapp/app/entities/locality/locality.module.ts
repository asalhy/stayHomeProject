import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { LocalityComponent } from './locality.component';
import { LocalityDetailComponent } from './locality-detail.component';
import { localityRoute } from './locality.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(localityRoute)],
  declarations: [LocalityComponent, LocalityDetailComponent]
})
export class StayHomeLocalityModule {}
