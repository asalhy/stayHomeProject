import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDemand, Demand } from 'app/shared/model/demand.model';
import { DemandService } from './demand.service';
import { ILocality } from 'app/shared/model/locality.model';
import { LocalityService } from 'app/entities/locality/locality.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IOrganization } from 'app/shared/model/organization.model';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { IServiceType } from 'app/shared/model/service-type.model';
import { ServiceTypeService } from 'app/entities/service-type/service-type.service';

type SelectableEntity = ILocality | IUser | IOrganization | IServiceType;

@Component({
  selector: 'jhi-demand-update',
  templateUrl: './demand-update.component.html'
})
export class DemandUpdateComponent implements OnInit {
  isSaving = false;
  localities: ILocality[] = [];
  users: IUser[] = [];
  organizations: IOrganization[] = [];
  servicetypes: IServiceType[] = [];
  creationDateDp: any;

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    phone: [null, [Validators.required]],
    email: [],
    description: [],
    status: [null, [Validators.required]],
    creationDate: [null, [Validators.required]],
    localityId: [null, Validators.required],
    assigneeId: [],
    organizationId: [null, Validators.required],
    serviceTypeId: [null, Validators.required]
  });

  constructor(
    protected demandService: DemandService,
    protected localityService: LocalityService,
    protected userService: UserService,
    protected organizationService: OrganizationService,
    protected serviceTypeService: ServiceTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demand }) => {
      this.updateForm(demand);

      this.localityService.query().subscribe((res: HttpResponse<ILocality[]>) => (this.localities = res.body || []));

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.organizationService.query().subscribe((res: HttpResponse<IOrganization[]>) => (this.organizations = res.body || []));

      this.serviceTypeService.query().subscribe((res: HttpResponse<IServiceType[]>) => (this.servicetypes = res.body || []));
    });
  }

  updateForm(demand: IDemand): void {
    this.editForm.patchValue({
      id: demand.id,
      firstName: demand.firstName,
      lastName: demand.lastName,
      phone: demand.phone,
      email: demand.email,
      description: demand.description,
      status: demand.status,
      creationDate: demand.creationDate,
      localityId: demand.localityId,
      assigneeId: demand.assigneeId,
      organizationId: demand.organizationId,
      serviceTypeId: demand.serviceTypeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demand = this.createFromForm();
    if (demand.id !== undefined) {
      this.subscribeToSaveResponse(this.demandService.update(demand));
    } else {
      this.subscribeToSaveResponse(this.demandService.create(demand));
    }
  }

  private createFromForm(): IDemand {
    return {
      ...new Demand(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      email: this.editForm.get(['email'])!.value,
      description: this.editForm.get(['description'])!.value,
      status: this.editForm.get(['status'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value,
      localityId: this.editForm.get(['localityId'])!.value,
      assigneeId: this.editForm.get(['assigneeId'])!.value,
      organizationId: this.editForm.get(['organizationId'])!.value,
      serviceTypeId: this.editForm.get(['serviceTypeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemand>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
