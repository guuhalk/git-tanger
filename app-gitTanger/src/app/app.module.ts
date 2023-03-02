import { PrimeNgModule } from './Shared/prime-ng/prime-ng.module';
import { MainModule } from './Pages/main/main.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './Shared/nav-bar/nav-bar.component';
import { CherryPickModule } from './Pages/cherry-pick/cherry-pick.module';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MainModule,
    PrimeNgModule,
    CherryPickModule


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
