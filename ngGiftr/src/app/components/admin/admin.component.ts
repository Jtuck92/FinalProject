import { NgForm } from '@angular/forms';
import { Budget } from './../../models/budget';
import { PrivatePost } from './../../models/private-post';
import { PrivateComment } from './../../models/private-comment';
import { PrivateEvent } from './../../models/private-event';
import { Payment } from './../../models/payment';
import { Gift } from 'src/app/models/gift';
import { EventType } from './../../models/event-type';
import { EventPost } from 'src/app/models/event-post';
import { EventComment } from './../../models/event-comment';
import { Address } from './../../models/address';
import { Component, OnInit } from '@angular/core';
import { GiftService } from './../../service/gift.service';
import { UserService } from './../../service/user.service';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { Event } from './../../models/event';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AddressService } from 'src/app/service/address.service';
import { BudgetService } from 'src/app/service/budget.service';
import { EventCommentService } from 'src/app/service/event-comment.service';
import { EventPostService } from 'src/app/service/event-post.service';
import { EventTypeService } from 'src/app/service/event-type.service';
import { PaymentService } from 'src/app/service/payment.service';
import { PrivateCommentService } from 'src/app/service/private-comment.service';
import { PrivatePostService } from 'src/app/service/private-post.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  constructor(
    private eventSvc: EventService,
    private addressSvc: AddressService,
    private budgetSvc: BudgetService,
    private eCommentSvc: EventCommentService,
    private ePostSvc: EventPostService,
    private eTypeSvc: EventTypeService,
    private giftSvc: GiftService,
    private paymentSvc: PaymentService,
    private privateCommetSvc: PrivateCommentService,
    private privatePostSvc: PrivatePostService,
    private privateEventSvc: PrivateEventService,
    private userSvc: UserService,
    private router: Router,
    private auth: AuthService
  ) {}
  events: Event[] = [];
  addresses: Address[] = [];
  users: User[] = [];
  eventComments: EventComment[] = [];
  eventPosts: EventPost[] = [];
  eventTypes: EventType[] = [];
  gifts: Gift[] = [];
  payments: Payment[] = [];
  privateEvents: PrivateEvent[] = [];
  privateEventComments: PrivateComment[] = [];
  privateEventPosts: PrivatePost[] = [];
  budgets: Budget[] = [];
  receivers: User[] = [];
  selected: Event = null;
  selectedListType = 'All';
  listTypes = [
    'All',
    'Events',
    'Users',
    'Event Posts',
    'Event Comments',
    'Private Events',
    'Private Event Posts',
    'Private Event Comments',
    'Addresses',
    'Payments',
    'Budgets',
    'Event Types',
    'Gifts',
  ];
  pageView = 'Dashboard';
  pageViews = [
    'Dashboard',
    'Content',
    'Add New Event',
    'Add New Budget',
    'Add New Event Type',
    'Update Event',
    'Update Budget'
  ];
  stringId = '';
  numUserId = 0;
  user = new User();
  updateBudget = new Budget();
  updateEvent = new Event();
  newEvent = new Event();
  newBudget = new Budget();
  newEventType = new EventType();
  errors = [];
  formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: 0,
  });

  ngOnInit(): void {
    window.scrollTo(0, 0);

    if (!localStorage.getItem('listType')) {
      localStorage.setItem('listType', this.selectedListType);
    } else {
      this.selectedListType = localStorage.getItem('listType');
    }
    if (!localStorage.getItem('pageView')) {
      localStorage.setItem('pageView', this.pageView);
    } else {
      this.pageView = localStorage.getItem('pageView');
    }
    this.stringId = localStorage.getItem('userId');
    // console.log(this.activeGifts);

    this.numUserId = parseInt(this.stringId);
    this.userSvc.show(this.numUserId).subscribe(
      (data) => {
        this.user = data;
        if (this.user.username != 'giftr') {
          this.router.navigateByUrl('notfound');
        }
      },
      (err) => {
        console.error('User retrive failed');
        this.router.navigateByUrl('notfound');
      }
    );

    // this.auth.isHomePageComponent(true);
    this.loadAddresses();
    this.loadBudgets();
    this.loadEventComments();
    this.loadEventPosts();
    this.loadEventTypes();
    this.loadEvents();
    this.loadGifts();
    this.loadPayments();
    this.loadPrivateEventComments();
    this.loadPrivateEventPosts();
    this.loadPrivateEvents();
    this.loadUsers();
  }
  // END ON INIT ======================================
  formatCurrency(num: number) {
    return this.formatter.format(num);
  }

  // START CLICK EVENTS ===================================================
  findEvent() {
    this.eventSvc.show(this.updateEvent.id).subscribe(
      (data) => {
        this.updateEvent = data;
        console.log(this.updateEvent);
      },
      (err) => {
        console.error('Admin ShowEvent(); retrieve failed');
      }
    );
  }
  findBudget() {
    this.budgetSvc.show(this.updateBudget.id).subscribe(
      (data) => {
        this.updateBudget = data;
        console.log(this.updateBudget);
      },
      (err) => {
        console.error('Admin ShowEvent(); retrieve failed');
      }
    );
  }

  createEventType() {
    this.errors = [];
    console.log(this.newEventType);
    if (this.newEventType.name == undefined) {
      this.errors.push(
        'Name will be the identifier. You may not leave this empty.'
      );
    }
    if (this.newEventType.description == undefined) {
      this.errors.push('Please Provide a Description');
    }
    if (this.newEventType.imageUrl == undefined) {
      this.errors.push('Please provide an image URL');
    }
    if (this.errors.length == 0) {
      this.eTypeSvc.create(this.newEventType).subscribe(
        (data) => {
          localStorage.removeItem('pageView');
          localStorage.setItem('pageView', 'Content');
          localStorage.removeItem('listType');
          localStorage.setItem('listType', 'Event Types');
          this.newEventType = new EventType();
          location.reload();
        },
        (err) => {
          console.error('Admin Show New Event Type(); retrieve failed');
        }
      );
    }
  }
  createBudget() {
    this.errors = [];
    this.newBudget.name =
      '' +
      this.formatCurrency(this.newBudget.lowPrice) +
      ' - ' +
      this.formatCurrency(this.newBudget.highPrice);
    console.log(this.newBudget);
    if (this.newBudget.name == undefined) {
      this.errors.push(
        'Low Price and High Price must be selected to create name'
      );
    }
    if (this.newBudget.lowPrice == undefined) {
      this.errors.push('Low Price must be selected');
    }
    if (this.newBudget.highPrice == undefined) {
      this.errors.push('High Price must be selected');
    }
    if (this.errors.length == 0) {
      this.budgetSvc.create(this.newBudget).subscribe(
        (data) => {
          localStorage.removeItem('pageView');
          localStorage.setItem('pageView', 'Content');
          localStorage.removeItem('listType');
          localStorage.setItem('listType', 'Budgets');
          this.newBudget = new Budget();
          location.reload();
        },
        (err) => {
          console.error('Admin ShowBudget(); retrieve failed');
        }
      );
    }
  }
  createEvent() {
    // console.log(this.newEvent);
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
          // console.log(Event);
          localStorage.removeItem('pageView');
          localStorage.setItem('pageView', 'Content');
          localStorage.removeItem('listType');
          localStorage.setItem('listType', 'Events');
          this.newBudget = new Budget();
          this.newEvent = new Event();
          location.reload();
        },
        (err) => {
          console.error('Admin LoadEvent(); retrieve failed');
        }
      );
    }
  }
  // START NON CREATE CLICK EVENTS ===================================================

  confirmCancelNewEvent(form: NgForm) {
    let response = confirm(
      'Are you sure you want to leave? Any changes will be lost'
    );
    if (response) {
      localStorage.removeItem('pageView');
      localStorage.setItem('pageView', 'Dashboard');
      form.reset();
      location.reload();
    }
  }
  confirmCancelNewBudget(form: NgForm) {
    let response = confirm(
      'Are you sure you want to leave? Any changes will be lost'
    );
    if (response) {
      localStorage.removeItem('pageView');
      localStorage.setItem('pageView', 'Dashboard');
      form.reset();
      location.reload();
    }
  }
  confirmCancelNewEventType(form: NgForm) {
    let response = confirm(
      'Are you sure you want to leave? Any changes will be lost'
    );
    if (response) {
      localStorage.removeItem('pageView');
      localStorage.setItem('pageView', 'Dashboard');
      form.reset();
      location.reload();
    }
  }

  setListTypeCookie() {
    localStorage.removeItem('listType');
    localStorage.setItem('listType', this.selectedListType);
  }

  setPageViewCookie() {
    localStorage.removeItem('pageView');
    localStorage.setItem('pageView', this.pageView);
  }
  // LOAD ALL EVENTS IN DB ======================================
  loadEvents(): void {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
      },
      (err) => {
        console.error('Admin LoadEvent(); retrieve failed');
      }
    );
  }
  // LOAD ALL USERS IN DB ====================================
  loadUsers(): void {
    this.userSvc.index().subscribe(
      (data) => {
        this.users = data;
        for (let i = 0; i < this.users.length; i++) {
          if (this.users[i].username == 'giftr') {
            this.users.splice(i, 1);
          }
        }
      },
      (err) => {
        console.error('Admin LoadUser(); retrieve failed');
      }
    );
  }
  // LOAD ALL Addresses IN DB ====================================
  loadAddresses(): void {
    this.addressSvc.index().subscribe(
      (data) => {
        this.addresses = data;
      },
      (err) => {
        console.error('Admin LoadAddresses(); retrieve failed');
      }
    );
  }
  // LOAD ALL Payments IN DB ====================================
  loadPayments(): void {
    this.paymentSvc.index().subscribe(
      (data) => {
        this.payments = data;
      },
      (err) => {
        console.error('Admin LoadPayments(); retrieve failed');
      }
    );
  }
  // LOAD ALL Event Comments IN DB ====================================
  loadEventComments(): void {
    this.eCommentSvc.index().subscribe(
      (data) => {
        this.eventComments = data;
      },
      (err) => {
        console.error('Admin LoadEventComments(); retrieve failed');
      }
    );
  }
  // LOAD ALL Event Posts IN DB ====================================
  loadEventPosts(): void {
    this.ePostSvc.index().subscribe(
      (data) => {
        this.eventPosts = data;
      },
      (err) => {
        console.error('Admin LoadEventPosts(); retrieve failed');
      }
    );
  }
  // LOAD ALL Gifts IN DB ====================================
  loadGifts(): void {
    this.giftSvc.index().subscribe(
      (data) => {
        this.gifts = data;
      },
      (err) => {
        console.error('Admin LoadGifts(); retrieve failed');
      }
    );
  }
  // LOAD ALL Budget Cat IN DB ====================================
  loadBudgets(): void {
    this.budgetSvc.index().subscribe(
      (data) => {
        this.budgets = data;
      },
      (err) => {
        console.error('Admin LoadBudgets(); retrieve failed');
      }
    );
  }
  // LOAD ALL Private Events IN DB ====================================
  loadPrivateEvents(): void {
    this.privateEventSvc.index().subscribe(
      (data) => {
        this.privateEvents = data;
      },
      (err) => {
        console.error('Admin LoadPrivateEvents(); retrieve failed');
      }
    );
  }
  // LOAD ALL Private Event Posts IN DB ====================================
  loadPrivateEventPosts(): void {
    this.privatePostSvc.index().subscribe(
      (data) => {
        this.privateEventPosts = data;
      },
      (err) => {
        console.error('Admin LoadPrivateEventPosts(); retrieve failed');
      }
    );
  }
  // LOAD ALL Private Event Comments IN DB ====================================
  loadPrivateEventComments(): void {
    this.privateCommetSvc.index().subscribe(
      (data) => {
        this.privateEventComments = data;
      },
      (err) => {
        console.error('Admin LoadPrivateEventComments(); retrieve failed');
      }
    );
  }
  // LOAD ALL Event types IN DB ====================================
  loadEventTypes(): void {
    this.eTypeSvc.index().subscribe(
      (data) => {
        this.eventTypes = data;
      },
      (err) => {
        console.error('Admin LoadEventTypes(); retrieve failed');
      }
    );
  }
  eventResult(event) {
    // console.log(event);

    this.selected = event;
    localStorage.setItem('event', '' + this.selected.id);
    this.router.navigateByUrl('/eventDetails');
  }
  findReceiverUsername(index) {
    if (this.receivers[index] == null) {
      return ' Check back Soon! ';
    }
    return this.receivers[index].username;
  }
  findReceiverAddressStreet(index) {
    // console.log(this.receivers[index]);
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.street;
  }
  findReceiverAddressStreet2(index) {
    // console.log([index]);
    if (this.receivers[index] == null) {
      return '';
    }
    return 'Ste/Apt/Unit: ' + this.receivers[index].address.street2;
  }
  findReceiverAddressCity(index) {
    // console.log([index]);
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.city + ', ';
  }
  findReceiverAddressState(index) {
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.stateProvince + ' ';
  }
  findReceiverAddressCountry(index) {
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.country;
  }
  findReceiverAddressZip(index) {
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.zip;
  }
  // DISABLE Event IN DB ====================================
  disableEvent(e) {
    this.eventSvc.destroy(e.id).subscribe(
      (data) => {
        console.log(data);
        this.loadEvents();
        location.reload();
      },
      (err) => {
        console.error(' Events disable failed');
      }
    );
  }
  // DISABLE Users IN DB ====================================
  disableUser(e) {
    this.userSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadUsers();
        location.reload();
      },
      (err) => {
        console.error(' Events disable failed');
      }
    );
  }
  // DISABLE Event Posts IN DB ====================================
  disableEventPost(e) {
    this.ePostSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadEventPosts();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }
  // DISABLE  Event Comments IN DB ====================================
  disableEventComments(e) {
    this.eCommentSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadEventComments();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }
  // DISABLE Private Events IN DB ====================================
  disablePrivateEvent(e) {
    this.privateEventSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadPrivateEvents();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }
  // DISABLE Private Event Posts IN DB ====================================
  disablePrivateEventPost(e) {
    this.privatePostSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadPrivateEventPosts();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }
  // DISABLE Private Event Comments IN DB ====================================
  disablePrivateEventComments(e) {
    this.privateCommetSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadPrivateEventPosts();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }
  // UPDATE Addresses IN DB ====================================
  updateAddress(e) {
    this.addressSvc.update(e.id).subscribe(
      (data) => {
        this.loadAddresses();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }
  // DISABLE Budgets IN DB ====================================
  disableBudget(e) {
    this.budgetSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadPrivateEventPosts();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }
  // DISABLE Event Types IN DB ====================================
  disableEventTypes(e) {
    this.eTypeSvc.destroy(e.id).subscribe(
      (data) => {
        this.loadEventTypes();
        location.reload();
      },
      (err) => {
        console.error(' Event Posts disable failed');
      }
    );
  }

  // Update Events IN DB ====================================

  changeEvent() {
    this.errors = [];
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

      this.updateEvent.budget = this.newBudget;
      this.updateEvent.eventManager = this.user;
      console.log(this.updateEvent);

      this.eventSvc.update(this.updateEvent).subscribe(
        (data) => {
          // console.log(Event);
          localStorage.removeItem('pageView');
          localStorage.setItem('pageView', 'Content');
          localStorage.removeItem('listType');
          localStorage.setItem('listType', 'Events');
          this.newBudget = new Budget();
          this.updateEvent = new Event();
          location.reload();
        },
        (err) => {
          console.error('Admin LoadEvent(); retrieve failed');
        }
      );
    }
  }
    // Update Budgets IN DB ====================================

  changeBudget() {
    this.updateBudget.name = '' +
    this.formatCurrency(this.updateBudget.lowPrice) +
    ' - ' +
    this.formatCurrency(this.updateBudget.highPrice);
    this.budgetSvc.update(this.updateBudget).subscribe(
      (data) => {
        // console.log(Event);
        localStorage.removeItem('pageView');
        localStorage.setItem('pageView', 'Content');
        localStorage.removeItem('listType');
        localStorage.setItem('listType', 'Budgets');
        this.updateBudget = new Budget();
        location.reload();
      },
      (err) => {
        console.error('Admin LoadEvent(); retrieve failed');
      }
    );
  }
}

// }
