import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WeatherPageComponent } from './weather-page/weather-page.component';
import { WeatherRoutingModule } from './weather-routing.module';
import { HourlyComponent } from "./components/hourly/hourly.component";
import { WeaklyComponent } from './components/weakly/weakly.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { GraphsComponent } from './components/graphs/graphs.component';
import { RightPaneComponent } from './components/right-pane/right-pane.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    WeatherPageComponent,
    HourlyComponent,
    WeaklyComponent,
    FooterComponent,
    HeaderComponent,
    GraphsComponent,
    RightPaneComponent,
  ],
  imports: [
    CommonModule,
    WeatherRoutingModule,
    ReactiveFormsModule
  ],
  bootstrap: [WeatherPageComponent]
})
export class WeatherModule {
  constructor(){

    console.log("weather module");
  }
  
 }
