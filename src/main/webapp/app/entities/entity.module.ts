import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'governorate',
        loadChildren: () => import('./governorate/governorate.module').then(m => m.StayHomeGovernorateModule)
      },
      {
        path: 'delegation',
        loadChildren: () => import('./delegation/delegation.module').then(m => m.StayHomeDelegationModule)
      },
      {
        path: 'locality',
        loadChildren: () => import('./locality/locality.module').then(m => m.StayHomeLocalityModule)
      },
      {
        path: 'service-type',
        loadChildren: () => import('./service-type/service-type.module').then(m => m.StayHomeServiceTypeModule)
      },
      {
        path: 'organization',
        loadChildren: () => import('./organization/organization.module').then(m => m.StayHomeOrganizationModule)
      },
      {
        path: 'demand',
        loadChildren: () => import('./demand/demand.module').then(m => m.StayHomeDemandModule)
      },
      {
        path: 'demand-audit',
        loadChildren: () => import('./demand-audit/demand-audit.module').then(m => m.StayHomeDemandAuditModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class StayHomeEntityModule {}
