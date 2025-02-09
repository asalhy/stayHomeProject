import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { OrganizationComponent } from './organization.component';
import { OrganizationDetailComponent } from './organization-detail.component';
import { OrganizationUpdateComponent } from './organization-update.component';
import { OrganizationDeleteDialogComponent } from './organization-delete-dialog.component';
import { organizationRoute } from './organization.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(organizationRoute)],
  declarations: [OrganizationComponent, OrganizationDetailComponent, OrganizationUpdateComponent, OrganizationDeleteDialogComponent],
  entryComponents: [OrganizationDeleteDialogComponent]
})
export class StayHomeOrganizationModule {}
