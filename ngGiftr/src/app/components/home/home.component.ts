import { Budget } from './../../models/budget';
import { Router } from '@angular/router';
import { AuthService } from './../../service/auth.service';
import { Event } from './../../models/event';
import { EventService } from './../../service/event.service';
import { Component, OnInit } from '@angular/core';
import { BudgetService } from 'src/app/service/budget.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private eventSvc: EventService,
     private budgetSvc: BudgetService,
      private auth: AuthService,
       private router: Router) { }
events: Event[] = [];
selected: Event = null;
selectedBudget = 0;
budgets: Budget [] = []

  ngOnInit(): void {
    this.auth.isHomePageComponent(true);
    this.loadEvents();
    this.loadBudgets();
  }
  loadEvents(): void {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
      },
      (err) => {
        console.error('HomeComponent.LoadEvents(); retrieve failed');

      }
    );
  }
  loadBudgets(): void {
    this.budgetSvc.index().subscribe(
      (data) => {
        this.budgets = data;
      },
      (err) => {
        console.error('HomeComponent.LoadBudgets(); retrieve failed');

      }
    );
  }
  eventResult(event){
    this.selected = event;
    localStorage.setItem('event' , "" + this.selected.id);
    this.router.navigateByUrl("/eventDetails");
  }
}
