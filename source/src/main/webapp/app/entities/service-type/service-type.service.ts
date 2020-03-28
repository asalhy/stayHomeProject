import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IServiceType } from 'app/shared/model/service-type.model';

type EntityResponseType = HttpResponse<IServiceType>;
type EntityArrayResponseType = HttpResponse<IServiceType[]>;

@Injectable({ providedIn: 'root' })
export class ServiceTypeService {
  public resourceUrl = SERVER_API_URL + 'api/service-types';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IServiceType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServiceType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
