import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { MunicipalityComponent } from './municipality.component';
import { MunicipalityDetailComponent } from './municipality-detail.component';
import { municipalityRoute } from './municipality.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(municipalityRoute)],
  declarations: [MunicipalityComponent, MunicipalityDetailComponent]
})
export class StayHomeMunicipalityModule {}
