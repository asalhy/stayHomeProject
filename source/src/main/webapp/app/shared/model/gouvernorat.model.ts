export interface IGouvernorat {
  id?: number;
  name?: string;
}

export class Gouvernorat implements IGouvernorat {
  constructor(public id?: number, public name?: string) {}
}
