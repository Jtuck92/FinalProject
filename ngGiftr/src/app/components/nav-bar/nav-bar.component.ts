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
  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {

  }

  checkUserLoggedIn(){
    return this.auth.checkLogin()?true:false;
   }

   searchResult(){
  this.router.navigateByUrl("search")
   }
}
