import { Moment } from 'moment';
import { IServiceType } from 'app/shared/model/service-type.model';

export interface IOrganization {
  id?: number;
  name?: string;
  requiresApproval?: boolean;
  defaultPhone?: string;
  creationDate?: Moment;
  serviceTypes?: IServiceType[];
}

export class Organization implements IOrganization {
  constructor(
    public id?: number,
    public name?: string,
    public requiresApproval?: boolean,
    public defaultPhone?: string,
    public creationDate?: Moment,
    public serviceTypes?: IServiceType[]
  ) {
    this.requiresApproval = this.requiresApproval || false;
  }
}
