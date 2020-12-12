import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PrivateEvent } from 'src/app/models/private-event';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/service/auth.service';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-private-event',
  templateUrl: './private-event.component.html',
  styleUrls: ['./private-event.component.css']
})
export class PrivateEventComponent implements OnInit {
  stringId: string;
  numUserId: number;
  userSrv: any;
  user: any;
  uEPipe: any;
  selectedPrivateEvent: any;

  constructor(private userSvc: UserService,
    private privateEventSvc: PrivateEventService,
    private auth: AuthService,
    private router: Router) { }

    users: User[] = [];
    selectedUser: User = null;
    events: Event[] = [];
    selected: PrivateEvent = new PrivateEvent();
    selectedEvent: PrivateEvent = null;
    privateEvent: PrivateEvent[] = [];


    newImageUrl: string = "";

  ngOnInit(): void {
    this.auth.isHomePageComponent(true);
    this.loadPrivateEvent();
  }
  loadUsers(): void {
    this.userSvc.index().subscribe(
      (data) => {
        this.users = data;
        // console.log(this.users);
      },
      (err) => {
        console.error('UserComponent.LoadUser(); retrieve failed');
      }
    );
  }
  userResult(user){
    this.selectedUser = user;
    localStorage.setItem('user', "" + this.selectedUser.id);
    this.router.navigateByUrl("/users");
  }
  loadPrivateEvent(): void {
    this.privateEventSvc.index().subscribe(
      (data) => {
        // console.log(data);
        this.privateEvent = data;
        // console.log(this.privateEvent);
      },
      (err) => {
        console.error('PrivateEventComponent.LoadPrivateEvent(); retrieve failed');
      }
    );
  }
  privateEventResult(privateEvent){
    this.selectedPrivateEvent = privateEvent;
    localStorage.setItem('privateEvent', "" + this.selectedPrivateEvent.id);
    this.router.navigateByUrl("/event/:id");
  }
  addImageUrl(url: string){
  // TODO
  }
}
