import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { GouvernoratComponent } from './gouvernorat.component';
import { GouvernoratDetailComponent } from './gouvernorat-detail.component';
import { gouvernoratRoute } from './gouvernorat.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(gouvernoratRoute)],
  declarations: [GouvernoratComponent, GouvernoratDetailComponent]
})
export class StayHomeGouvernoratModule {}
