import { Moment } from 'moment';
import { IDemandAudit } from 'app/shared/model/demand-audit.model';
import { DemandStatus } from 'app/shared/model/enumerations/demand-status.model';

export interface IDemand {
  id?: number;
  firstName?: string;
  lastName?: string;
  phone?: string;
  email?: string;
  description?: string;
  status?: DemandStatus;
  creationDate?: Moment;
  demandAudits?: IDemandAudit[];
  localityId?: number;
  assigneeId?: number;
  organizationId?: number;
  serviceTypeId?: number;
}

export class Demand implements IDemand {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public phone?: string,
    public email?: string,
    public description?: string,
    public status?: DemandStatus,
    public creationDate?: Moment,
    public demandAudits?: IDemandAudit[],
    public localityId?: number,
    public assigneeId?: number,
    public organizationId?: number,
    public serviceTypeId?: number
  ) {}
}
