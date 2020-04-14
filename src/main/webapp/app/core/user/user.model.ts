import { Organization } from 'app/shared/model/organization.model';
import { ServiceType } from 'app/shared/model/service-type.model';
import { Locality } from 'app/shared/model/locality.model';

export interface IUser {
  id?: any;
  login?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
  organization?: Organization;
  serviceTypes?: ServiceType[];
  localities?: Locality[];
  cin?: string;
  phone?: string;
  groupName?: string;
  membershipId?: string;
}

export class User implements IUser {
  constructor(
    public id?: any,
    public login?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: string[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public password?: string,
    public organization?: Organization,
    public serviceTypes?: ServiceType[],
    public localities?: Locality[],
    public cin?: string,
    public phone?: string,
    public membershipId?: string,
    public groupName?: string
  ) {}
}
