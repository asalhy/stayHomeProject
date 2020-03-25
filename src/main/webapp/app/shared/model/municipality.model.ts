export interface IMunicipality {
  id?: number;
  name?: string;
  delegationName?: string;
  delegationId?: number;
}

export class Municipality implements IMunicipality {
  constructor(public id?: number, public name?: string, public delegationName?: string, public delegationId?: number) {}
}
