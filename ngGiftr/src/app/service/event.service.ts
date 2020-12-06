import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  url = environment.baseUrl + 'api/events';

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



  index(): Observable<Event[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<Event[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(eventId: number): Observable<Event> {
      this.url = environment.baseUrl + 'api/events';
      const httpOptions = this.gethttpOptions();
      return this.http.get<Event>(`${this.url}/${eventId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('EventService.show(): Error retrieving Event ' + eventId);
        })
        );
      }

      create(event: Event){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/events';
        return this.http.post<any>(this.url, event, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating Event');
          })
          );
        }
        update(event: Event){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/events/' + event.id;
          return this.http.put<any>(this.url, event, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating Event');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/events/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating Event');
         })
       );
       }


}
