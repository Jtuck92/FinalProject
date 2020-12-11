import { UserService } from './../../service/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Event } from './../../models/event';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css'],
})
export class EventDetailsComponent implements OnInit {
  constructor(
    private eventSvc: EventService,
    private pEventSrv: PrivateEventService,
    private auth: AuthService,
    private router: Router,
    private userSrv: UserService
  ) {}
  events: Event[];
  selected: Event = new Event();
  idString = null;
  loggedIn = false;
  stringId = null;
  numUserId = null;
  user: User = new User();



  ngOnInit(): void {
    if (this.auth.checkLogin) {
      this.loggedIn = true;
    }
    window.scrollTo(0,0);

    this.idString = localStorage.getItem('event');
    this.idString = parseInt(this.idString);
    this.eventSvc.show(this.idString).subscribe(
      (data) => {
        this.selected = data;
        console.log(this.selected);
        // localStorage.removeItem('event');
      },
      (err) => {
        this.router.navigateByUrl('notFound');
      }
    );
    this.stringId = localStorage.getItem('userId');
    // console.log(this.activeGifts);

    this.numUserId = parseInt(this.stringId);
    this.userSrv.show(this.numUserId).subscribe(
      (data) => {
        this.user = data;
      },
      (err) => {
        console.error('User retrieve failed');
      }
    );


  }
  catch(error) {
    this.router.navigateByUrl('notFound');
  }
  pEventSignup() {
    if(!this.auth.checkLogin()){
    this.router.navigateByUrl("login");
    }else{

      console.log(this.selected.users);
      console.log("this is the logged in User" + this.user);
      for(let i = 0; i < this.selected.users.length; i++){
        if(this.selected.users[i].id == this.user.id){
          alert("You have already signed up for this event. Check your profile for additional details")
          return "";
        }
      }
      localStorage.setItem('event' , "" + this.selected.id);
      this.router.navigateByUrl('eventSignup');
    }

  }
}
