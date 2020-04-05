import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILocality } from 'app/shared/model/locality.model';

@Component({
  selector: 'jhi-locality-detail',
  templateUrl: './locality-detail.component.html'
})
export class LocalityDetailComponent implements OnInit {
  locality: ILocality | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locality }) => (this.locality = locality));
  }

  previousState(): void {
    window.history.back();
  }
}
