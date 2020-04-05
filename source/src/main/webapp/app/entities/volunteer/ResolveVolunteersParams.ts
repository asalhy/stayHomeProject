import { ActivatedRouteSnapshot, Resolve } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ResolveVolunteersParams implements Resolve<any> {
  resolve(route: ActivatedRouteSnapshot): any {
    return {
      municipalityId: route.queryParams.municipalityId
    };
  }
}
