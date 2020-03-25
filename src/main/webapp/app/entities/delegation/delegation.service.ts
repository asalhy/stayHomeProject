import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDelegation } from 'app/shared/model/delegation.model';

type EntityResponseType = HttpResponse<IDelegation>;
type EntityArrayResponseType = HttpResponse<IDelegation[]>;

@Injectable({ providedIn: 'root' })
export class DelegationService {
  public resourceUrl = SERVER_API_URL + 'api/delegations';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDelegation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDelegation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
