import { User } from './../../models/user';
import { GiftService } from './../../service/gift.service';
import { BudgetService } from './../../service/budget.service';
import { Budget } from './../../models/budget';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Event } from './../../models/event';
import { Gift } from 'src/app/models/gift';
import { UserService } from 'src/app/service/user.service';

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
    private budgetsSvc: BudgetService,
    private giftSvc: GiftService,
    private userSrv: UserService
  ) {}

  events: Event[];
  selected: Event = new Event();
  idString = null;
  loggedIn = false;
  budgets: Budget[];
  gift: Gift = new Gift();
  user: User = null;
  stringId = '';
  numUserId = 0;

  ngOnInit(): void {
    if (this.auth.checkLogin) {
      this.loggedIn = true;
    }
    // console.log(this.selected);

    this.idString = localStorage.getItem('event');
    this.idString = parseInt(this.idString);
    this.eventSvc.show(this.idString).subscribe(
      (data) => {
        this.selected = data;
        // console.log(this.selected);
        // localStorage.removeItem('event');
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
      this.gift.event = this.selected;
      // console.log(this.gift.event);
      this.stringId = localStorage.getItem('userId');
      // console.log(this.stringId);
      this.numUserId = parseInt(this.stringId);
      // console.log(this.numUserId);



      this.userSrv.show(this.numUserId).subscribe(
        (data) => {
          this.user = data;
          // console.log('Inside Show');
          // console.log(this.user);
          this.selected.users.push(this.user);
          // console.log(this.selected.users);

          this.gift.gifter = this.user; //TODO find user
          this.eventSvc.update(this.selected).subscribe(
            (data) => {
              // console.log('Inside Update');
              this.selected = data;
              // console.log(this.selected.users);
            },
            (err) => {
              console.error("This is the Event Update Failing ");
              ;
            }
          );
          this.giftSvc.create(this.gift).subscribe(
            (data) => {
              this.gift = data;
              // console.log(this.gift);
            },
            (err) => {
              this.router.navigateByUrl('notFound');
            }
          );

          this.router.navigateByUrl('profile');
        },
        (err) => {
          console.error('User retrive failed');
        }
        );
        // console.log('After Show');

  }

  loadBudgets(): void {
    this.budgetsSvc.index().subscribe(
      (data) => {
        this.budgets = data;
        // console.log(this.budgets);
      },
      (err) => {
        console.error('WorkoutComponent.LoadWorkout(); retrive failed');
      }
    );
  }
}
