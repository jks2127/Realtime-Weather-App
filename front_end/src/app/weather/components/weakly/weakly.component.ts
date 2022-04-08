import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-weakly',
  templateUrl: './weakly.component.html',
  styleUrls: ['./weakly.component.scss']
})
export class WeaklyComponent implements OnInit {
  @Input() weatherData?: any;
  todayDate!: String;
  constructor() { }

  ngOnInit(): void {
    // getting today date in "2022-08-04" format to check and not to show in the list
    this.todayDate = formatDate(new Date(), 'yyyy-MM-dd','en-IN');    
  }

}
