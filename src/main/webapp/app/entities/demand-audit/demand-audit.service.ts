import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDemandAudit } from 'app/shared/model/demand-audit.model';

type EntityResponseType = HttpResponse<IDemandAudit>;
type EntityArrayResponseType = HttpResponse<IDemandAudit[]>;

@Injectable({ providedIn: 'root' })
export class DemandAuditService {
  public resourceUrl = SERVER_API_URL + 'api/demand-audits';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDemandAudit>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDemandAudit[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(demandAudit: IDemandAudit): IDemandAudit {
    const copy: IDemandAudit = Object.assign({}, demandAudit, {
      creationDate:
        demandAudit.creationDate && demandAudit.creationDate.isValid() ? demandAudit.creationDate.format(DATE_FORMAT) : undefined
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
      res.body.forEach((demandAudit: IDemandAudit) => {
        demandAudit.creationDate = demandAudit.creationDate ? moment(demandAudit.creationDate) : undefined;
      });
    }
    return res;
  }
}
