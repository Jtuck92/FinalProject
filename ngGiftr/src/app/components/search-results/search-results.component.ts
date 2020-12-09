import { AuthService } from 'src/app/service/auth.service';
import { EventService } from './../../service/event.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { startWith, withLatestFrom, map } from 'rxjs/operators';
import { Event } from './../../models/event';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})

export class SearchResultsComponent implements OnInit {
  events: Event[];
  filteredEvents: Event[];
  foodItem: FoodItem;
  searchString: string;
  formGroup: FormGroup;

  ngOnInit(): void {
    this.auth.isHomePageComponent(true);
    this.getEvents();
    this.searchString = this.currentRoute.snapshot.paramMap.get('param');
  }
  constructor(private formBuilder: FormBuilder, private eventSvc: EventService, public currentRoute: ActivatedRoute, private auth: AuthService) {
    this.formGroup = formBuilder.group({ filter: [''] });



  }


  private getEvents() {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
        console.log(this.events);
      },
      (err) => {
        console.error("Events retrieve failed");

      }
    );


}
}

export interface FoodItem {
  name: string;
}


