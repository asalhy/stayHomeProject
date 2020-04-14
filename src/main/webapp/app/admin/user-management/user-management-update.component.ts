import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { LANGUAGES } from 'app/core/language/language.constants';
import { User } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { IServiceType, ServiceType } from 'app/shared/model/service-type.model';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { ServiceTypeService } from 'app/entities/service-type/service-type.service';

import { GovernorateService } from 'app/entities/governorate/governorate.service';
import { Delegation, IDelegation } from 'app/shared/model/delegation.model';
import { Governorate, IGovernorate } from 'app/shared/model/governorate.model';
import { DelegationService } from 'app/entities/delegation/delegation.service';
import { LocalityService } from 'app/entities/locality/locality.service';
import { Locality } from 'app/shared/model/locality.model';
import { Demand } from 'app/shared/model/demand.model';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-user-mgmt-update',
  templateUrl: './user-management-update.component.html'
})
export class UserManagementUpdateComponent implements OnInit {
  user!: User;
  languages = LANGUAGES;
  authorities: string[] = [];
  isSaving = false;

  governorates: Governorate[] = [];
  delegations: Delegation[] = [];
  localitiesList: Locality[] = [];
  demand = new Demand();

  organizations: Organization[] = [];
  serviceTypesForm: ServiceType[] = [];

  serviceTypes: ServiceType[];
  selectedGovernorate = new Governorate();
  selectedOrganization = new Organization();
  localities: Locality[] = [];

  editForm = this.fb.group({
    id: [],
    login: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50), Validators.pattern('^[_.@A-Za-z0-9-]*')]],
    firstName: ['', [Validators.maxLength(50)]],
    lastName: ['', [Validators.maxLength(50)]],
    email: ['', [Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    activated: [],
    langKey: [],
    authorities: [],
    organizationId: [null, Validators.required],
    serviceTypesForm: [[], [Validators.required]],
    governorateId: [null, Validators.required],
    delegationId: [null, Validators.required],
    localitiesForm: [null, Validators.required],
    groupName: [],
    membershipId: [],
    cin: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
    phone: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(8)]]
  });

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private governorateService: GovernorateService,
    private delegationService: DelegationService,
    private localityService: LocalityService,
    private organizationService: OrganizationService,
    private serviceTypesService: ServiceTypeService
  ) {
    this.serviceTypes = [];
    this.localities = [];
  }

  ngOnInit(): void {
    this.governorateService.query().subscribe(response => {
      this.governorates = response.body as Governorate[];
    });

    this.organizationService.query().subscribe((res: HttpResponse<IOrganization[]>) => (this.organizations = res.body || []));

    this.route.data.subscribe(({ user }) => {
      if (user) {
        this.user = user;
        if (this.user.id === undefined) {
          this.user.activated = true;
        }
        this.updateForm(user);
      }
    });
    this.userService.authorities().subscribe(authorities => {
      this.authorities = authorities;
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    this.updateUser(this.user);
    if (this.user.id !== undefined) {
      this.userService.update(this.user).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    } else {
      this.userService.create(this.user).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    }
  }

  private updateForm(user: User): void {
    this.editForm.patchValue({
      id: user.id,
      login: user.login,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      activated: user.activated,
      langKey: user.langKey,
      authorities: user.authorities,
      cin: user.cin,
      organization: user.organization,
      serviceTypes: user.serviceTypes,
      localities: user.localities,
      phone: user.phone
    });
  }

  private updateUser(user: User): void {
    user.login = this.editForm.get(['login'])!.value;
    user.firstName = this.editForm.get(['firstName'])!.value;
    user.lastName = this.editForm.get(['lastName'])!.value;
    user.email = this.editForm.get(['email'])!.value;
    user.activated = this.editForm.get(['activated'])!.value;
    user.langKey = this.editForm.get(['langKey'])!.value;
    user.authorities = this.editForm.get(['authorities'])!.value;
    user.organization = this.selectedOrganization;
    user.serviceTypes = this.serviceTypes;
    user.localities = this.localities;
    user.groupName = this.editForm.get(['groupName'])!.value;
    user.membershipId = this.editForm.get(['membershipId'])!.value;
    user.cin = this.editForm.get(['cin'])!.value;
    user.phone = this.editForm.get(['phone'])!.value;
  }

  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }

  getDelegationSelect(gouvernorat: IGovernorate): void {
    this.delegations = [];
    this.localitiesList = [];
    this.selectedGovernorate = gouvernorat;
    this.delegationService.query({ governorateId: gouvernorat.id }).subscribe(response => {
      this.delegations = response.body || [];
    });
  }

  getLocalitySelect(delegation: IDelegation): void {
    this.localitiesList = [];
    this.localityService.query({ delegationId: delegation.id }).subscribe(response => {
      this.localitiesList = response.body || [];
    });
  }

  getServiceSelect(organization: IOrganization): void {
    this.editForm.get(['organizationId'])!.setValue(organization.id);
    this.selectedOrganization = organization as Organization;
    this.serviceTypesForm = organization.serviceTypes as ServiceType[];
  }

  selectServiceType(serviceType: IServiceType): void {
    this.editForm.get(['serviceTypes'])!.setValue(serviceType.id);
    this.serviceTypes = this.editForm.get(['serviceTypes'])!.value;
  }
}
