import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILocality } from 'app/shared/model/locality.model';

type EntityResponseType = HttpResponse<ILocality>;
type EntityArrayResponseType = HttpResponse<ILocality[]>;

@Injectable({ providedIn: 'root' })
export class LocalityService {
  public resourceUrl = SERVER_API_URL + 'api/localities';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILocality>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILocality[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
