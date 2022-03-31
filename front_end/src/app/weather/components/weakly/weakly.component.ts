import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-weakly',
  templateUrl: './weakly.component.html',
  styleUrls: ['./weakly.component.scss']
})
export class WeaklyComponent implements OnInit {
  @Input() dataObj?: any;
  todayDate: any;
  constructor() { }

  ngOnInit(): void {
    this.todayDate = formatDate(new Date(), 'yyyy-MM-dd','en-IN');
  }

}
