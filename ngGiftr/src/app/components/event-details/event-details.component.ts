import { UserInEventPipe } from './../../pipes/user-in-event.pipe';
import { EventPostService } from './../../service/event-post.service';
import { UserService } from './../../service/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Event } from './../../models/event';
import { User } from 'src/app/models/user';
import { EventPost } from 'src/app/models/event-post';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css'],
})
export class EventDetailsComponent implements OnInit {
  constructor(
    private buttonPipe: UserInEventPipe,
    private eventSvc: EventService,
    private pEventSrv: PrivateEventService,
    private auth: AuthService,
    private router: Router,
    private userSrv: UserService,
    private eventPostSvc: EventPostService
  ) {}
  events: Event[];
  selected: Event = new Event();
  idString = null;
  loggedIn = false;
  stringId = null;
  numUserId = null;
  user: User = new User();
  eventPost = new EventPost;
  button = false;



  ngOnInit(): void {
    this.auth.isHomePageComponent(true);
    if (this.auth.checkLogin) {
      this.loggedIn = true;
    }
    window.scrollTo(0,0);
    this.idString = localStorage.getItem('event');
    this.idString = parseInt(this.idString);
    this.eventSvc.show(this.idString).subscribe(
      (data) => {
        this.selected = data;
        // console.log(this.selected);
        // localStorage.removeItem('event');
        this.stringId = localStorage.getItem('userId');
        // console.log(this.activeGifts);

        this.numUserId = parseInt(this.stringId);
        this.userSrv.show(this.numUserId).subscribe(
          (data) => {
            this.user = data;
           this.button = this.buttonPipe.transform(this.selected.users, this.user.id);
          },
        (err) => {
          console.error('User retrieve failed');
        }
      );
      },
      (err) => {
        this.router.navigateByUrl('notFound');
      }
      );


  }
  catch(error) {
    this.router.navigateByUrl('notFound');
  }
  pEventSignup() {
    if(!this.auth.checkLogin()){
    this.router.navigateByUrl("/login");
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
      this.router.navigateByUrl('/eventSignup');
    }

  }

  submitEventPost() {
    if(!this.auth.checkLogin()){
      this.router.navigateByUrl("/login");
    } else {
      this.eventPost.user = this.user;
      this.eventPost.event = this.selected;
      for(let i = 0; i < this.selected.users.length; i++){
        if(this.selected.users[i].id == this.user.id) {
          this.eventPostSvc.create(this.eventPost).subscribe(
            (data) => {
              this.eventPost = data;
              localStorage.setItem('eventPost' , "" + this.selected.id);
              this.router.navigateByUrl('/gallery');
            },
            (err) => {
              this.router.navigateByUrl('notFound');
            }
          );
        }
      }
    }
  }

  // ##TODO Need to address the alert, can still post a picture even if not tied to an event.

}
