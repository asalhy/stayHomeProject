import { IOrganization } from 'app/shared/model/organization.model';

export interface IServiceType {
  id?: number;
  name?: string;
  organizations?: IOrganization[];
}

export class ServiceType implements IServiceType {
  constructor(public id?: number, public name?: string, public organizations?: IOrganization[]) {}
}
