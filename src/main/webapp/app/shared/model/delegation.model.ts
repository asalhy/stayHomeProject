export interface IDelegation {
  id?: number;
  name?: string;
  governorateId?: number;
}

export class Delegation implements IDelegation {
  constructor(public id?: number, public name?: string, public governorateId?: number) {}
}
