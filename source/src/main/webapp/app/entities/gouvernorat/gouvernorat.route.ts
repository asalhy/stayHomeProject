import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGouvernorat, Gouvernorat } from 'app/shared/model/gouvernorat.model';
import { GouvernoratService } from './gouvernorat.service';
import { GouvernoratComponent } from './gouvernorat.component';
import { GouvernoratDetailComponent } from './gouvernorat-detail.component';

@Injectable({ providedIn: 'root' })
export class GouvernoratResolve implements Resolve<IGouvernorat> {
  constructor(private service: GouvernoratService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGouvernorat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gouvernorat: HttpResponse<Gouvernorat>) => {
          if (gouvernorat.body) {
            return of(gouvernorat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Gouvernorat());
  }
}

export const gouvernoratRoute: Routes = [
  {
    path: '',
    component: GouvernoratComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'stayHomeApp.gouvernorat.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GouvernoratDetailComponent,
    resolve: {
      gouvernorat: GouvernoratResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'stayHomeApp.gouvernorat.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
