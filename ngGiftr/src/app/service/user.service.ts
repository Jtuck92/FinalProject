import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = environment.baseUrl + 'api/users';

  gethttpOptions(){
    const  credentials = this.auth.getCredentials();

    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${credentials}`,
        })
      };
  return httpOptions;

  }
constructor(private http: HttpClient,private auth: AuthService) { }



  index(): Observable<User[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<User[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(userId: number): Observable<User> {
      this.url = environment.baseUrl + 'api/users';
      const httpOptions = this.gethttpOptions();
      return this.http.get<User>(`${this.url}/${userId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('UserService.show(): Error retrieving User ' + userId);
        })
        );
      }

      create(user: User){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/users';
        return this.http.post<any>(this.url, user, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating User');
          })
          );
        }
        update(user: User){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/users/' + user.id;
          return this.http.put<any>(this.url, user, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating User');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/users/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating User');
         })
       );
       }


}
