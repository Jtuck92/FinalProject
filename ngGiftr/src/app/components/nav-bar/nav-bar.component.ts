import { UserService } from './../../service/user.service';
import { User } from './../../models/user';
import { AuthService } from './../../service/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  isCollapsed = true;
  loggedIn = false;
  search = "";
  user = new User();
  idString = null;
  numUserId = null;
  isAdmin = false;
  profile = 'profile'
  constructor(private auth: AuthService, private router: Router, private userSrv: UserService) { }

  ngOnInit(): void {
    if(localStorage.getItem('userId')){
      this.idString = localStorage.getItem('userId');
        this.numUserId = parseInt(this.idString);
        this.userSrv.show(this.numUserId).subscribe(
          (data) => {
            this.user = data;
            if(this.user.id == 4){
              this.isAdmin = true;}
            if(this.isAdmin){
              this.profile = 'admin'
            }
          },
        (err) => {
          console.error('User retrieve failed');
        }
      );

    }

  }

  checkUserLoggedIn(){
    return this.auth.checkLogin()?true:false;
   }

   searchResult(){
     localStorage.removeItem('search');
     localStorage.setItem('search' , this.search);
     this.router.navigateByUrl("/search/" + this.search);

   }


}
