import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGouvernorat } from 'app/shared/model/gouvernorat.model';

type EntityResponseType = HttpResponse<IGouvernorat>;
type EntityArrayResponseType = HttpResponse<IGouvernorat[]>;

@Injectable({ providedIn: 'root' })
export class GouvernoratService {
  public resourceUrl = SERVER_API_URL + 'api/gouvernorats';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGouvernorat>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGouvernorat[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
