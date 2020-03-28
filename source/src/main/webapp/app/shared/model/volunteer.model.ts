import { Moment } from 'moment';
import { IServiceType } from 'app/shared/model/service-type.model';

export interface IVolunteer {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phone?: string;
  creationDate?: Moment;
  serviceTypes?: IServiceType[];
  municipalityName?: string;
  municipalityId?: number;
}

export class Volunteer implements IVolunteer {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phone?: string,
    public creationDate?: Moment,
    public serviceTypes?: IServiceType[],
    public municipalityName?: string,
    public municipalityId?: number
  ) {}
}
