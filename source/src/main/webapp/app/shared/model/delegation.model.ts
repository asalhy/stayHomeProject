export interface IDelegation {
  id?: number;
  name?: string;
  gouvernoratName?: string;
  gouvernoratId?: number;
}

export class Delegation implements IDelegation {
  constructor(public id?: number, public name?: string, public gouvernoratName?: string, public gouvernoratId?: number) {}
}
