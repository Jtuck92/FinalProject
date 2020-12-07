import { Event } from './../../models/event';
import { EventService } from './../../service/event.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private eventSvc: EventService) { }
events: Event[] = [];
  ngOnInit(): void {
    this.loadEvents();
  }
  loadEvents(): void {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
        console.log(this.events);
      },
      (err) => {
        console.error('WorkoutComponent.LoadWorkout(); retrive failed');
      }
    );
  }

}
