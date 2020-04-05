import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDemandAudit, DemandAudit } from 'app/shared/model/demand-audit.model';
import { DemandAuditService } from './demand-audit.service';
import { DemandAuditComponent } from './demand-audit.component';
import { DemandAuditDetailComponent } from './demand-audit-detail.component';

@Injectable({ providedIn: 'root' })
export class DemandAuditResolve implements Resolve<IDemandAudit> {
  constructor(private service: DemandAuditService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDemandAudit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((demandAudit: HttpResponse<DemandAudit>) => {
          if (demandAudit.body) {
            return of(demandAudit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DemandAudit());
  }
}

export const demandAuditRoute: Routes = [
  {
    path: '',
    component: DemandAuditComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'stayHomeApp.demandAudit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DemandAuditDetailComponent,
    resolve: {
      demandAudit: DemandAuditResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'stayHomeApp.demandAudit.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
