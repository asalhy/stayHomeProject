import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILocality, Locality } from 'app/shared/model/locality.model';
import { LocalityService } from './locality.service';
import { LocalityComponent } from './locality.component';
import { LocalityDetailComponent } from './locality-detail.component';

@Injectable({ providedIn: 'root' })
export class LocalityResolve implements Resolve<ILocality> {
  constructor(private service: LocalityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILocality> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((locality: HttpResponse<Locality>) => {
          if (locality.body) {
            return of(locality.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Locality());
  }
}

export const localityRoute: Routes = [
  {
    path: '',
    component: LocalityComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'stayHomeApp.locality.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LocalityDetailComponent,
    resolve: {
      locality: LocalityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'stayHomeApp.locality.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
