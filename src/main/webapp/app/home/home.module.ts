import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild([HOME_ROUTE]), NgSelectModule],
  declarations: [HomeComponent]
})
export class StayHomeHomeModule {}
