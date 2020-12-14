import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Budget } from 'src/app/models/budget';
import { PrivateEvent } from 'src/app/models/private-event';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/service/auth.service';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-private-event',
  templateUrl: './private-event.component.html',
  styleUrls: ['./private-event.component.css'],
})
export class PrivateEventComponent implements OnInit {
  stringId: string;
  numUserId: number;
  userSrv: any;
  user: any;
  uEPipe: any;
  selectedPrivateEvent: any;
  errors: any[];
  newEvent: any;
  newBudget: any;
  budgetSvc: any;
  eventSvc: any;

  constructor(
    private userSvc: UserService,
    private privateEventSvc: PrivateEventService,
    private auth: AuthService,
    private router: Router
  ) {}
  idString = null;
  users: User[] = [];
  selectedUser: User = null;
  events: Event[] = [];
  selected: PrivateEvent = new PrivateEvent();
  selectedEvent: PrivateEvent = null;
  privateEvent: PrivateEvent[] = [];

  newImageUrl: string = '';

  ngOnInit(): void {
    window.scrollTo(0,0);
    this.auth.isHomePageComponent(true);
    this.loadPrivateEvent();
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
  loadUsers(): void {
    this.userSvc.index().subscribe(
      (data) => {
        this.users = data;
      },
      (err) => {
        console.error('UserComponent.LoadUser(); retrieve failed');
      }
    );
  }
  createPrivateEvent() {
    this.errors = [];
    if (this.newEvent.name == undefined) {
      this.errors.push('Giftr name must be filled out');
    }
    if (this.newEvent.description == undefined) {
      this.errors.push('Giftr description must be filled defined');
    }
    if (this.newEvent.startDate == undefined) {
      this.errors.push('Giftr start date must be selected');
    }
    if (this.newEvent.endDate == undefined) {
      this.errors.push('Giftr end date must be selected');
    }
    if (this.newBudget == null) {
      this.errors.push('Giftr budget must be selected');
    }
    if (this.newEvent.imageUrl == undefined) {
      this.errors.push('Giftr image must be provided');
    }
    if (this.errors.length == 0) {
      this.budgetSvc.show(this.newBudget.id).subscribe(
        (data) => {
          this.newBudget = data;
          console.log(this.newBudget);
        },
        (err) => {
          console.error('Admin ShowBudget(); retrieve failed');
        }
      );

      this.newEvent.budget = this.newBudget;
      this.newEvent.eventManager = this.user;
      console.log(this.newEvent);

      this.eventSvc.create(this.newEvent).subscribe(
        (data) => {
          localStorage.removeItem('pageView');
          localStorage.setItem('pageView', 'Content');
          localStorage.removeItem('listType');
          localStorage.setItem('listType', 'Events');
          this.newBudget = new Budget();
          this.newEvent = new PrivateEvent();
          location.reload();
        },
        (err) => {
          console.error('Admin LoadEvent(); retrieve failed');
        }
      );
    }
  }
  userResult(user) {
    this.selectedUser = user;
    localStorage.setItem('user', '' + this.selectedUser.id);
    this.router.navigateByUrl('/users');
  }
  loadPrivateEvent(): void {
    this.privateEventSvc.index().subscribe(
      (data) => {
        this.privateEvent = data;
      },
      (err) => {
        console.error(
          'PrivateEventComponent.LoadPrivateEvent(); retrieve failed'
        );
      }
    );
  }
  privateEventResult(privateEvent) {
    this.selectedPrivateEvent = privateEvent;
    localStorage.setItem('privateEvent', '' + this.selectedPrivateEvent.id);
    this.router.navigateByUrl('/event/:id');
  }
  addImageUrl(url: string) {
    // TODO
  }
}
