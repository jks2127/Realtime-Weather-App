import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from "@angular/forms";
import { NgrxIncrementDecrementComponent } from './ngrx-increment-decrement/ngrx-increment-decrement.component';
import { StoreModule } from '@ngrx/store';
import { counterReducer } from './reducers/incDec.reducer';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
@NgModule({
  declarations: [
    AppComponent,
    NgrxIncrementDecrementComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    StoreModule.forRoot({count: counterReducer}),
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
