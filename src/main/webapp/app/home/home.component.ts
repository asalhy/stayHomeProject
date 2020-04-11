import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { GovernorateService } from 'app/entities/governorate/governorate.service';
import { Delegation, IDelegation } from 'app/shared/model/delegation.model';
import { Governorate, IGovernorate } from 'app/shared/model/governorate.model';
import { DelegationService } from 'app/entities/delegation/delegation.service';
import { LocalityService } from 'app/entities/locality/locality.service';
import { Locality } from 'app/shared/model/locality.model';
import { Demand, IDemand } from 'app/shared/model/demand.model';
import { HttpResponse } from '@angular/common/http';
import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { IServiceType, ServiceType } from 'app/shared/model/service-type.model';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { ServiceTypeService } from 'app/entities/service-type/service-type.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DemandService } from 'app/entities/demand/demand.service';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;

  governorates: Governorate[] = [];
  delegations: Delegation[] = [];
  localities: Locality[] = [];

  organizations: Organization[] = [];
  serviceTypes: ServiceType[] = [];

  selectedServiceType = new ServiceType();
  selectedGovernorate = new Governorate();
  demand = new Demand();
  selectedOrganization = new Organization();

  demandSent = false;
  isSaving = false;
  createDemandFrom = this.resetDemandFrom();

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
      this.governorates = response.body as Governorate[];
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
    this.selectedGovernorate = gouvernorat;
    this.delegationService.query({ governorateId: gouvernorat.id }).subscribe(response => {
      this.delegations = response.body || [];
    });
  }

  getLocalitySelect(delegation: IDelegation): void {
    this.localities = [];
    this.createDemandFrom.get(['localityId'])!.setValue(null);
    this.localityService.query({ delegationId: delegation.id }).subscribe(response => {
      this.localities = response.body || [];
    });
  }

  getServiceSelect(organization: IOrganization): void {
    this.createDemandFrom.get(['organizationId'])!.setValue(organization.id);
    this.selectedOrganization = organization as Organization;
    this.serviceTypes = organization.serviceTypes as ServiceType[];
  }

  selectServiceType(serviceType: IServiceType): void {
    this.createDemandFrom.get(['serviceTypeId'])!.setValue(serviceType.id);
    this.selectedServiceType = serviceType;
  }

  save(): void {
    this.isSaving = true;
    this.demand = this.createFromForm();
    this.subscribeToSaveResponse(this.demandService.create(this.demand));
  }

  private resetDemandFrom(): FormGroup {
    return this.fb.group({
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      phone: [null, [Validators.required]],
      email: [],
      description: [],
      governorateId: [null, Validators.required],
      delegationId: [null, Validators.required],
      localityId: [null, Validators.required],
      organizationId: [null, Validators.required],
      serviceTypeId: [null, Validators.required]
    });
  }

  private createFromForm(): IDemand {
    return {
      ...new Demand(),
      firstName: this.createDemandFrom.get(['firstName'])!.value,
      lastName: this.createDemandFrom.get(['lastName'])!.value,
      phone: this.createDemandFrom.get(['phone'])!.value,
      email: this.createDemandFrom.get(['email'])!.value,
      description: this.createDemandFrom.get(['description'])!.value,
      localityId: this.createDemandFrom.get(['localityId'])!.value,
      organizationId: this.createDemandFrom.get(['organizationId'])!.value,
      serviceTypeId: this.createDemandFrom.get(['serviceTypeId'])!.value
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
    this.demandSent = true;
  }

  protected onSaveError(): void {
    this.isSaving = false;
    this.demandSent = false;
  }

  resetForm(): void {
    this.selectedGovernorate = new Governorate();
    this.selectedServiceType = new ServiceType();
    this.selectedOrganization = new Organization();
    this.createDemandFrom = this.resetDemandFrom();
  }
}
