import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { decrement, increment, reset } from '../actions/incDec.actions';

@Component({
  selector: 'app-ngrx-increment-decrement',
  templateUrl: './ngrx-increment-decrement.component.html',
  styleUrls: ['./ngrx-increment-decrement.component.scss']
})
export class NgrxIncrementDecrementComponent {
  counterObservable: Observable<number>;
  constructor(private store: Store<{count: number}>) {
    this.counterObservable = store.select('count');
   }

  dispatchFn(type: string) {
    switch (type) {
      case "increment":
        this.store.dispatch(increment())
        break;
      case "decrement":
        this.store.dispatch(decrement())
        break;

      default:
        this.store.dispatch(reset())
        break;
    }
    console.log("-----------", type);

    
  }
}
