import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { GovernorateService } from 'app/entities/governorate/governorate.service';
import { IDelegation } from 'app/shared/model/delegation.model';
import { IGovernorate } from 'app/shared/model/governorate.model';
import { DelegationService } from 'app/entities/delegation/delegation.service';
import { LocalityService } from 'app/entities/locality/locality.service';
import { ILocality } from 'app/shared/model/locality.model';
import { Demand, IDemand } from 'app/shared/model/demand.model';
import { HttpResponse } from '@angular/common/http';
import { IOrganization } from 'app/shared/model/organization.model';
import { IServiceType, ServiceType } from 'app/shared/model/service-type.model';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { ServiceTypeService } from 'app/entities/service-type/service-type.service';
import { FormBuilder, Validators } from '@angular/forms';
import { DemandService } from 'app/entities/demand/demand.service';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;

  governorates: IGovernorate[] | null = [];
  delegations: IDelegation[] | null = [];
  localities: ILocality[] | null = [];

  organizations: IOrganization[] | null = [];
  serviceTypes: ServiceType[] | null = [];
  selectedServiceType: IServiceType | undefined;

  isSaving = false;
  createDemandeFrom = this.fb.group({
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    phone: [null, [Validators.required]],
    email: [],
    description: [],
    localityId: [null, Validators.required],
    organizationId: [null, Validators.required],
    serviceTypeId: [null, Validators.required]
  });

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private governorateService: GovernorateService,
    private delegationService: DelegationService,
    private localityService: LocalityService,
    private organizationService: OrganizationService,
    private serviceTypeService: ServiceTypeService,
    private demandService: DemandService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.governorateService.query().subscribe(response => {
      this.governorates = response.body;
    });

    this.organizationService.query().subscribe((res: HttpResponse<IOrganization[]>) => (this.organizations = res.body || []));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  getDelegationSelect(gouvernorat: IGovernorate): void {
    this.delegations = [];
    this.localities = [];
    this.delegationService.query({ governorateId: gouvernorat.id }).subscribe(response => {
      this.delegations = response.body || [];
    });
  }

  getLocalitySelect(delegation: IDelegation): void {
    this.localities = [];
    this.createDemandeFrom.get(['localityId'])!.setValue(null);
    this.localityService.query({ delegationId: delegation.id }).subscribe(response => {
      this.localities = response.body || [];
    });
  }

  getServiceSelect(organization: IOrganization): void {
    this.createDemandeFrom.get(['organizationId'])!.setValue(organization.id);
    this.serviceTypeService
      .query({ organizationId: organization.id })
      .subscribe((res: HttpResponse<IServiceType[]>) => (this.serviceTypes = res.body || []));
  }

  selectServiceType(serviceType: IServiceType): void {
    this.createDemandeFrom.get(['serviceTypeId'])!.setValue(serviceType.id);
    this.selectedServiceType = serviceType;
  }

  save(): void {
    this.isSaving = true;
    const demand = this.createFromForm();
    this.subscribeToSaveResponse(this.demandService.create(demand));
  }

  private createFromForm(): IDemand {
    return {
      ...new Demand(),
      id: this.createDemandeFrom.get(['id'])!.value,
      firstName: this.createDemandeFrom.get(['firstName'])!.value,
      lastName: this.createDemandeFrom.get(['lastName'])!.value,
      phone: this.createDemandeFrom.get(['phone'])!.value,
      email: this.createDemandeFrom.get(['email'])!.value,
      description: this.createDemandeFrom.get(['description'])!.value,
      localityId: this.createDemandeFrom.get(['localityId'])!.value,
      organizationId: this.createDemandeFrom.get(['organizationId'])!.value,
      serviceTypeId: this.createDemandeFrom.get(['serviceTypeId'])!.value
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
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
