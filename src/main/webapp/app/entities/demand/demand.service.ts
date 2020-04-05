import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDemand } from 'app/shared/model/demand.model';

type EntityResponseType = HttpResponse<IDemand>;
type EntityArrayResponseType = HttpResponse<IDemand[]>;

@Injectable({ providedIn: 'root' })
export class DemandService {
  public resourceUrl = SERVER_API_URL + 'api/demands';

  constructor(protected http: HttpClient) {}

  create(demand: IDemand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demand);
    return this.http
      .post<IDemand>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(demand: IDemand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demand);
    return this.http
      .put<IDemand>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDemand>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDemand[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(demand: IDemand): IDemand {
    const copy: IDemand = Object.assign({}, demand, {
      creationDate: demand.creationDate && demand.creationDate.isValid() ? demand.creationDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.creationDate = res.body.creationDate ? moment(res.body.creationDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((demand: IDemand) => {
        demand.creationDate = demand.creationDate ? moment(demand.creationDate) : undefined;
      });
    }
    return res;
  }
}
