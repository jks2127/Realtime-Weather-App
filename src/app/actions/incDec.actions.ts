import { createAction } from "@ngrx/store";

export const increment = createAction('[increment] increment');
export const decrement = createAction('[decrement] decrement');
export const reset = createAction('[reset] reset');