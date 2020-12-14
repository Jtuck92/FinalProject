import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { EventComment } from '../models/event-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class EventCommentService {
  url = environment.baseUrl + 'api/eventComments';

  gethttpOptions(){
    const  credentials = this.auth.getCredentials();

    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Comment':  'application/json',
          'Authorization': `Basic ${credentials}`,
        })
      };
  return httpOptions;

  }
  gethttpOptionsLoggedIn(){
    const  credentials = localStorage.getItem('credentials');

    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Comment':  'application/json',
          'Authorization': `Basic ${credentials}`,
        })
      };
  return httpOptions;

  }
constructor(private http: HttpClient,private auth: AuthService) { }



  index(): Observable<EventComment[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<EventComment[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(eventCommentId: number): Observable<EventComment> {
      this.url = environment.baseUrl + 'api/eventComments';
      const httpOptions = this.gethttpOptions();
      return this.http.get<EventComment>(`${this.url}/${eventCommentId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('EventCommentService.show(): Error retrieving EventComment ' + eventCommentId);
        })
        );
      }

      create(eventComment: EventComment){
        const httpOptions = this.gethttpOptionsLoggedIn();
        this.url = environment.baseUrl + 'api/eventComments';
        return this.http.post<any>(this.url, eventComment, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating EventComment');
          })
          );
        }
        update(eventComment: EventComment){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/eventComments/' + eventComment.id;
          return this.http.put<any>(this.url, eventComment, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating EventComment');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/eventComments/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating EventComment');
         })
       );
       }

}
