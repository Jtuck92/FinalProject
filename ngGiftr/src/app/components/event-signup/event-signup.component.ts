import { BudgetService } from './../../service/budget.service';
import { Budget } from './../../models/budget';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Event } from './../../models/event';

@Component({
  selector: 'app-event-signup',
  templateUrl: './event-signup.component.html',
  styleUrls: ['./event-signup.component.css'],
})
export class EventSignupComponent implements OnInit {
  constructor(
    private eventSvc: EventService,
    private pEventSrv: PrivateEventService,
    private auth: AuthService,
    private router: Router,
    private budgetsSvc: BudgetService
  ) {}

  events: Event[];
  selected: Event = new Event();
  idString = null;
  loggedIn = false;
  budgets: Budget[];

  ngOnInit(): void {
    if (this.auth.checkLogin) {
      this.loggedIn = true;
    }
    console.log(this.selected);

    this.idString = localStorage.getItem('event');
    this.idString = parseInt(this.idString);
    this.eventSvc.show(this.idString).subscribe(
      (data) => {
        this.selected = data;
        console.log(this.selected);
        localStorage.removeItem('event');
      },
      (err) => {
        this.router.navigateByUrl('notFound');
      }
    );
  }
  catch(error) {
    this.router.navigateByUrl('notFound');
  }
  pEventSignupDone() {
    localStorage.setItem('event', '' + this.selected.id);
    this.router.navigateByUrl('profile');
  }

  loadBudgets(): void {
    this.budgetsSvc.index().subscribe(
      (data) => {
        this.budgets = data;
        console.log(this.budgets);
      },
      (err) => {
        console.error('WorkoutComponent.LoadWorkout(); retrive failed');
      }
    );
  }
}
