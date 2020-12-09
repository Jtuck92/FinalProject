import { User } from 'src/app/models/user';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
user: User = null;
  constructor(private authService: AuthService, private router: Router) { }



  login(registerForm: NgForm) {
    const user = registerForm.value;
    console.log(user);
    this.authService.login(user.username, user.password).subscribe(
      (data) => {
        this.user = data;
        console.log(localStorage.getItem("userId"));

        this.router.navigateByUrl('/profile');
        console.log('User Login');
      },
      (err) => {
        console.log('error on Login*******');
      }
    );
  }
  ngOnInit(): void {
  }

}
