import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WeatherPageComponent } from './weather-page/weather-page.component';

const routes: Routes = [
    { 
        path: '', 
        component: WeatherPageComponent
    },
]

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class WeatherRoutingModule {}