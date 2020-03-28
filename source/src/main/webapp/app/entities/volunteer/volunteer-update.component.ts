import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVolunteer, Volunteer } from 'app/shared/model/volunteer.model';
import { VolunteerService } from './volunteer.service';
import { IMunicipality } from 'app/shared/model/municipality.model';
import { MunicipalityService } from 'app/entities/municipality/municipality.service';

@Component({
  selector: 'jhi-volunteer-update',
  templateUrl: './volunteer-update.component.html'
})
export class VolunteerUpdateComponent implements OnInit {
  isSaving = false;
  municipalities: IMunicipality[] = [];
  creationDateDp: any;

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    email: [null, [Validators.required]],
    phone: [null, [Validators.required]],
    creationDate: [null, [Validators.required]],
    municipalityId: []
  });

  constructor(
    protected volunteerService: VolunteerService,
    protected municipalityService: MunicipalityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ volunteer }) => {
      this.updateForm(volunteer);

      this.municipalityService.query().subscribe((res: HttpResponse<IMunicipality[]>) => (this.municipalities = res.body || []));
    });
  }

  updateForm(volunteer: IVolunteer): void {
    this.editForm.patchValue({
      id: volunteer.id,
      firstName: volunteer.firstName,
      lastName: volunteer.lastName,
      email: volunteer.email,
      phone: volunteer.phone,
      creationDate: volunteer.creationDate,
      municipalityId: volunteer.municipalityId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const volunteer = this.createFromForm();
    if (volunteer.id !== undefined) {
      this.subscribeToSaveResponse(this.volunteerService.update(volunteer));
    } else {
      this.subscribeToSaveResponse(this.volunteerService.create(volunteer));
    }
  }

  private createFromForm(): IVolunteer {
    return {
      ...new Volunteer(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      email: this.editForm.get(['email'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value,
      municipalityId: this.editForm.get(['municipalityId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVolunteer>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IMunicipality): any {
    return item.id;
  }
}
