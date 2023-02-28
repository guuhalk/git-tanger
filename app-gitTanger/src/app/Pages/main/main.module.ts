import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import {InputTextModule} from 'primeng/inputtext';

@NgModule({
  declarations: [MainComponent],
  imports: [CommonModule,InputTextModule],
})
export class MainModule {}
