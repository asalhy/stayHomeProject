export interface IServiceType {
  id?: number;
  name?: string;
  volunteerId?: number;
}

export class ServiceType implements IServiceType {
  constructor(public id?: number, public name?: string, public volunteerId?: number) {}
}
