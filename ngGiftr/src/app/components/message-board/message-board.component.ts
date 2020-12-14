import { UserService } from './../../service/user.service';
import { EventComment } from './../../models/event-comment';
import { EventCommentService } from './../../service/event-comment.service';
import { EventPostService } from 'src/app/service/event-post.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EventPost } from 'src/app/models/event-post';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-message-board',
  templateUrl: './message-board.component.html',
  styleUrls: ['./message-board.component.css'],
})
export class MessageBoardComponent implements OnInit {
  constructor(
    private userSrv: UserService,
    private eventPostSvc: EventPostService,
    private auth: AuthService,
    private router: Router,
    private eventCommentSvc: EventCommentService
  ) {}
  comSelected: EventComment = new EventComment();
  events: EventPost[];
  selected: EventPost = new EventPost();
  idString = null;
  loggedIn = false;
  newComment = new EventComment();
  stringId = null;
  numUserId = null;
  user = new User();
isHomePage = true;

  ngOnInit(): void {
    this.auth.isHomePageComponent(this.isHomePage);
    if (!localStorage.getItem('foo')) {
      // console.log("Setting Foo");

      localStorage.setItem('foo', 'no reload');
      location.reload();
    } else {
      localStorage.removeItem('foo');

      window.scrollTo(0,0);
      if (this.auth.checkLogin) {
        this.loggedIn = true;
      }
      this.idString = localStorage.getItem('eventPost');
      // console.log(this.idString);
      this.idString = parseInt(this.idString);
      this.eventPostSvc.show(this.idString).subscribe(
        (data) => {
          // console.log(data);
          this.selected = data;
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
            // console.log(this.selected);
          },
          (err) => {
            // this.router.navigateByUrl('notFound');
            console.error("User not logged in");
          }
          );
        }
      }
        pEventSignup() {
        localStorage.setItem('eventPost', '' + this.selected.id);
      }
      postComment() {
        this.auth.isHomePageComponent(false);
        if(!this.auth.checkLogin()) {
          this.router.navigateByUrl('login')
        } else {
          this.newComment.post = this.selected;
          this.newComment.user = this.user;
          console.log(this.newComment);

      this.eventCommentSvc.create(this.newComment).subscribe(
        (good) => {
          console.log(good);

          this.selected.comments.push(good);
          location.reload();
        },
        (bad) => {
          console.error(bad);
        }
      );
      this.newComment = new EventComment();
    }
  }


}
