import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import {InputTextModule} from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [MainComponent],
  imports: [
    CommonModule,
    InputTextModule,
    ButtonModule
  ],
})
export class MainModule {}
