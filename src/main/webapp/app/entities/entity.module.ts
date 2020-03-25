import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'volunteer',
        loadChildren: () => import('./volunteer/volunteer.module').then(m => m.StayHomeVolunteerModule)
      },
      {
        path: 'gouvernorat',
        loadChildren: () => import('./gouvernorat/gouvernorat.module').then(m => m.StayHomeGouvernoratModule)
      },
      {
        path: 'delegation',
        loadChildren: () => import('./delegation/delegation.module').then(m => m.StayHomeDelegationModule)
      },
      {
        path: 'municipality',
        loadChildren: () => import('./municipality/municipality.module').then(m => m.StayHomeMunicipalityModule)
      },
      {
        path: 'service-type',
        loadChildren: () => import('./service-type/service-type.module').then(m => m.StayHomeServiceTypeModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class StayHomeEntityModule {}
