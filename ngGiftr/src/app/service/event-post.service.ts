import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { EventPost } from '../models/event-post';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class EventPostService {
  url = environment.baseUrl + 'api/eventPosts';

  gethttpOptions(){
    const  credentials = this.auth.getCredentials();

    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Post':  'application/json',
          'Authorization': `Basic ${credentials}`,
        })
      };
  return httpOptions;

  }
constructor(private http: HttpClient,private auth: AuthService) { }



  index(): Observable<EventPost[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<EventPost[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(eventPostId: number): Observable<EventPost> {
      this.url = environment.baseUrl + 'api/eventPosts';
      const httpOptions = this.gethttpOptions();
      return this.http.get<EventPost>(`${this.url}/${eventPostId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('EventPostService.show(): Error retrieving EventPost ' + eventPostId);
        })
        );
      }

      create(eventPost: EventPost){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/eventPosts';
        return this.http.post<any>(this.url, eventPost, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating EventPost');
          })
          );
        }
        update(eventPost: EventPost){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/eventPosts/' + eventPost.id;
          return this.http.put<any>(this.url, eventPost, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating EventPost');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/eventPosts/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating EventPost');
         })
       );
       }

}
