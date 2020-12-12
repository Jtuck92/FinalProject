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
  constructor(private auth: AuthService, private router: Router, private userSrv: UserService) { }

  ngOnInit(): void {

  }

  checkUserLoggedIn(){
    return this.auth.checkLogin()?true:false;
   }

   searchResult(){
     localStorage.removeItem('search');
     localStorage.setItem('search' , this.search);
     this.router.navigateByUrl("/search/" + this.search);

   }

checkUser(){
  this.idString = localStorage.getItem('userId');
    this.numUserId = parseInt(this.idString);
  this.userSrv.show(this.numUserId).subscribe(
    (data) => {
      this.user = data;
        if(this.user.id == 4){
          this.router.navigateByUrl("admin")
        }else{
          this.router.navigateByUrl("profile")
      },
    (err) => {
      console.error('User retrieve failed');
    }
  );


}





}
