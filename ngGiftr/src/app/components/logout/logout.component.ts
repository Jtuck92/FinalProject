import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  logout() {
    console.log("LOGOUT DONE");
    this.authService.logout();
    this.router.navigateByUrl('/login');

  }
  ngOnInit(): void {
  }

}
