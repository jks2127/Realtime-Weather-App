import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-weekly',
  templateUrl: './weekly.component.html',
  styleUrls: ['./weekly.component.scss']
})
export class WeeklyComponent implements OnInit {
  @Input() weatherData?: any;
  todayDate!: String;
  constructor() { }

  ngOnInit(): void {
    // getting today date in "2022-08-04" format to check and not to show in the list
    this.todayDate = formatDate(new Date(), 'yyyy-MM-dd','en-IN');    
  }

}
