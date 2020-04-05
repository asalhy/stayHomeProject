import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StayHomeSharedModule } from 'app/shared/shared.module';
import { ServiceTypeComponent } from './service-type.component';
import { ServiceTypeDetailComponent } from './service-type-detail.component';
import { ServiceTypeUpdateComponent } from './service-type-update.component';
import { ServiceTypeDeleteDialogComponent } from './service-type-delete-dialog.component';
import { serviceTypeRoute } from './service-type.route';

@NgModule({
  imports: [StayHomeSharedModule, RouterModule.forChild(serviceTypeRoute)],
  declarations: [ServiceTypeComponent, ServiceTypeDetailComponent, ServiceTypeUpdateComponent, ServiceTypeDeleteDialogComponent],
  entryComponents: [ServiceTypeDeleteDialogComponent]
})
export class StayHomeServiceTypeModule {}
