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
errors = [];


  login(registerForm: NgForm) {
    this.errors = [];
    const user = registerForm.value;
    // console.log(user);
    this.authService.login(user.username, user.password).subscribe(
      (data) => {
        this.user = data;
        // console.log(localStorage.getItem("userId"));
        if( this.user.username == 'giftr'){
          this.router.navigateByUrl('/admin');
        }else{
          this.router.navigateByUrl('/profile');

        }
        // console.log('User Login');
      },
      (err) => {
        this.errors.push("Your username or password was not found. Please try again. ")
        console.error('error on Login*******');
      }
    );
  }
  ngOnInit(): void {
    window.scroll(0,0);
  }

}
