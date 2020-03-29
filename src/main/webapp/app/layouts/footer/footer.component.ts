import { Component } from '@angular/core';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-footer',
  templateUrl: './footer.component.html',
  styles: ['.cursor-pointer {cursor: pointer}']
})
export class FooterComponent {
  constructor(private loginModalService: LoginModalService, private accountService: AccountService) {}

  login(): void {
    this.loginModalService.open();
  }

  public isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }
}
