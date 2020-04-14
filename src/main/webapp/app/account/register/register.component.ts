import { Component, AfterViewInit, ElementRef, ViewChild, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, ValidatorFn } from '@angular/forms';
import { JhiLanguageService } from 'ng-jhipster';

import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from 'app/shared/constants/error.constants';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { RegisterService } from './register.service';

import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { IServiceType, ServiceType } from 'app/shared/model/service-type.model';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { ServiceTypeService } from 'app/entities/service-type/service-type.service';

import { GovernorateService } from 'app/entities/governorate/governorate.service';
import { Delegation, IDelegation } from 'app/shared/model/delegation.model';
import { Governorate, IGovernorate } from 'app/shared/model/governorate.model';
import { User } from 'app/core/user/user.model.ts';
import { DelegationService } from 'app/entities/delegation/delegation.service';
import { LocalityService } from 'app/entities/locality/locality.service';
import { Locality } from 'app/shared/model/locality.model';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements AfterViewInit, OnInit {
  @ViewChild('login', { static: false })
  login?: ElementRef;

  doNotMatch = false;
  error = false;
  errorEmailExists = false;
  errorUserExists = false;
  success = false;
  showGroupInfo = false;
  registerForm: FormGroup;
  governorates: Governorate[] = [];
  delegations: Delegation[] = [];
  localities: Locality[] = [];
  organizations: Organization[] = [];
  serviceTypes: IServiceType[] = [];

  constructor(
    private languageService: JhiLanguageService,
    private loginModalService: LoginModalService,
    private registerService: RegisterService,
    private fb: FormBuilder,
    private governorateService: GovernorateService,
    private delegationService: DelegationService,
    private localityService: LocalityService,
    private organizationService: OrganizationService,
    private serviceTypesService: ServiceTypeService
  ) {
    this.registerForm = this.createRegisterForm();
  }

  createRegisterForm(): FormGroup {
    return this.fb.group({
      firstName: ['', [Validators.maxLength(50)]],
      lastName: ['', [Validators.maxLength(50)]],
      cin: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
      phone: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(10)]],
      // login: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50), Validators.pattern('^[_.@A-Za-z0-9-]*$')]],
      email: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
      organization: [null, Validators.required],
      serviceTypes: [[], [Validators.required]],
      governorate: [null, Validators.required],
      delegation: [null, Validators.required],
      localities: [[], Validators.required],
      groupName: [],
      membershipId: []
    });
  }

  ngOnInit(): void {
    this.governorateService.query().subscribe(response => {
      this.governorates = response.body as Governorate[];
    });

    this.organizationService.query().subscribe((res: HttpResponse<IOrganization[]>) => (this.organizations = res.body || []));
  }

  ngAfterViewInit(): void {
    if (this.login) {
      this.login.nativeElement.focus();
    }
  }

  register(): void {
    this.doNotMatch = false;
    this.error = false;
    this.errorEmailExists = false;
    this.errorUserExists = false;

    const password = this.registerForm.get(['password'])!.value;
    if (password !== this.registerForm.get(['confirmPassword'])!.value) {
      this.doNotMatch = true;
    } else {
      const user = this.createFromForm();
      this.registerService.save(user).subscribe(
        () => (this.success = true),
        response => this.processError(response)
      );
    }
  }

  private createFromForm(): User {
    return {
      ...new User(),
      login: this.registerForm.get(['email'])!.value,
      email: this.registerForm.get(['email'])!.value,
      cin: this.registerForm.get(['cin'])!.value,
      organization: this.registerForm.get(['organization'])!.value,
      serviceTypes: this.registerForm.get(['serviceTypes'])!.value,
      localities: this.registerForm.get(['localities'])!.value,
      firstName: this.registerForm.get(['firstName'])!.value,
      lastName: this.registerForm.get(['lastName'])!.value,
      phone: this.registerForm.get(['phone'])!.value,
      password: this.registerForm.get(['password'])!.value,
      langKey: this.languageService.getCurrentLanguage(),
      membershipId: this.registerForm.get(['membershipId'])!.value,
      groupName: this.registerForm.get(['groupName'])!.value
    };
  }

  openLogin(): void {
    this.loginModalService.open();
  }

  private processError(response: HttpErrorResponse): void {
    if (response.status === 400 && response.error.type === LOGIN_ALREADY_USED_TYPE) {
      this.errorUserExists = true;
    } else if (response.status === 400 && response.error.type === EMAIL_ALREADY_USED_TYPE) {
      this.errorEmailExists = true;
    } else {
      this.error = true;
    }
  }

  onSelectGovernorate(governorate?: IGovernorate): void {
    this.delegations = [];
    this.localities = [];

    if (governorate) {
      this.delegationService.query({ governorateId: governorate.id }).subscribe(response => {
        this.delegations = response.body || [];
      });
    }
  }

  onSelectDelegation(delegation?: IDelegation): void {
    this.localities = [];

    if (delegation) {
      this.localityService.query({ delegationId: delegation.id }).subscribe(response => {
        this.localities = response.body || [];
      });
    }
  }

  onSelectOrganization(organization?: IOrganization): void {
    this.serviceTypes = (organization?.serviceTypes as ServiceType[]) || [];
    this.showGroupInfo = organization?.requiresApproval || false;

    let validators: ValidatorFn[] = [];
    if (this.showGroupInfo) {
      validators = [Validators.required];
    }

    this.registerForm.get(['membershipId'])!.setValidators(validators);
    this.registerForm.get(['groupName'])!.setValidators(validators);
  }
}
