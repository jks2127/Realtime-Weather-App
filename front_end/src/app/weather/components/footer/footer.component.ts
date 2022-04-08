import { Component, OnInit, Output,EventEmitter } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  @Output() menuToggleValue = new EventEmitter();
  toggle:boolean = false;
  constructor() { }

  ngOnInit(): void {
  }
  menuToggle() {
    this.menuToggleValue.emit(!this.toggle);    
  }

}
