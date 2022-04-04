import { importExpr } from '@angular/compiler/src/output/output_ast';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { from } from 'rxjs';
import { NgrxIncrementDecrementComponent } from "./ngrx-increment-decrement/ngrx-increment-decrement.component";
import { WeatherPageComponent } from './weather/weather-page/weather-page.component';

const routes: Routes = [
  {
    path: 'ngrx',
    component: NgrxIncrementDecrementComponent,
  },
  {
    path: '',
    loadChildren: () => import('./weather/weather.module').then(m => m.WeatherModule)
    // component: WeatherPageComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
