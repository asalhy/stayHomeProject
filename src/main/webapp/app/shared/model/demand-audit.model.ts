import { Moment } from 'moment';
import { DemandStatus } from 'app/shared/model/enumerations/demand-status.model';

export interface IDemandAudit {
  id?: number;
  status?: DemandStatus;
  description?: string;
  ipAddress?: string;
  creationDate?: Moment;
  userId?: number;
  demandId?: number;
}

export class DemandAudit implements IDemandAudit {
  constructor(
    public id?: number,
    public status?: DemandStatus,
    public description?: string,
    public ipAddress?: string,
    public creationDate?: Moment,
    public userId?: number,
    public demandId?: number
  ) {}
}
