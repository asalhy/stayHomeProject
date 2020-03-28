import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { VolunteerComponent } from './volunteer.component';
import { VolunteerDetailComponent } from './volunteer-detail.component';
import { VolunteerUpdateComponent } from './volunteer-update.component';
import { VolunteerDeleteDialogComponent } from './volunteer-delete-dialog.component';
import { volunteerRoute } from './volunteer.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(volunteerRoute)],
  declarations: [VolunteerComponent, VolunteerDetailComponent, VolunteerUpdateComponent, VolunteerDeleteDialogComponent],
  entryComponents: [VolunteerDeleteDialogComponent]
})
export class StayHomeVolunteerModule {}
