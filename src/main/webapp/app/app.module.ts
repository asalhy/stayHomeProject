import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { StayHomeSharedModule } from 'app/shared/shared.module';
import { StayHomeCoreModule } from 'app/core/core.module';
import { StayHomeAppRoutingModule } from './app-routing.module';
import { StayHomeHomeModule } from './home/home.module';
import { StayHomeEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    StayHomeSharedModule,
    StayHomeCoreModule,
    StayHomeHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    StayHomeEntityModule,
    StayHomeAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class StayHomeAppModule {}
