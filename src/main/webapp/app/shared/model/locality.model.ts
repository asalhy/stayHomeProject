export interface ILocality {
  id?: number;
  name?: string;
  postalCode?: string;
  delegationId?: number;
}

export class Locality implements ILocality {
  constructor(public id?: number, public name?: string, public postalCode?: string, public delegationId?: number) {}
}
