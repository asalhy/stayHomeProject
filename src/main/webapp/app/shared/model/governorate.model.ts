export interface IGovernorate {
  id?: number;
  name?: string;
}

export class Governorate implements IGovernorate {
  constructor(public id?: number, public name?: string) {}
}
