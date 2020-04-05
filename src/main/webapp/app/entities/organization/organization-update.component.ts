import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { OrganizationService } from './organization.service';
import { IServiceType } from 'app/shared/model/service-type.model';
import { ServiceTypeService } from 'app/entities/service-type/service-type.service';

@Component({
  selector: 'jhi-organization-update',
  templateUrl: './organization-update.component.html'
})
export class OrganizationUpdateComponent implements OnInit {
  isSaving = false;
  servicetypes: IServiceType[] = [];
  creationDateDp: any;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    requiresApproval: [null, [Validators.required]],
    defaultPhone: [null, [Validators.required]],
    creationDate: [null, [Validators.required]],
    serviceTypes: []
  });

  constructor(
    protected organizationService: OrganizationService,
    protected serviceTypeService: ServiceTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organization }) => {
      this.updateForm(organization);

      this.serviceTypeService.query().subscribe((res: HttpResponse<IServiceType[]>) => (this.servicetypes = res.body || []));
    });
  }

  updateForm(organization: IOrganization): void {
    this.editForm.patchValue({
      id: organization.id,
      name: organization.name,
      requiresApproval: organization.requiresApproval,
      defaultPhone: organization.defaultPhone,
      creationDate: organization.creationDate,
      serviceTypes: organization.serviceTypes
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organization = this.createFromForm();
    if (organization.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationService.update(organization));
    } else {
      this.subscribeToSaveResponse(this.organizationService.create(organization));
    }
  }

  private createFromForm(): IOrganization {
    return {
      ...new Organization(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      requiresApproval: this.editForm.get(['requiresApproval'])!.value,
      defaultPhone: this.editForm.get(['defaultPhone'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value,
      serviceTypes: this.editForm.get(['serviceTypes'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganization>>): void {
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

  trackById(index: number, item: IServiceType): any {
    return item.id;
  }

  getSelected(selectedVals: IServiceType[], option: IServiceType): IServiceType {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
