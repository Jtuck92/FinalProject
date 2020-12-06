import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { EventType } from '../models/event-type';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class EventTypeService {
  url = environment.baseUrl + 'api/eventTypes';

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



  index(): Observable<EventType[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<EventType[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(eventTypeId: number): Observable<EventType> {
      this.url = environment.baseUrl + 'api/eventTypes';
      const httpOptions = this.gethttpOptions();
      return this.http.get<EventType>(`${this.url}/${eventTypeId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('EventTypeService.show(): Error retrieving EventType ' + eventTypeId);
        })
        );
      }

      create(eventType: EventType){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/eventTypes';
        return this.http.post<any>(this.url, eventType, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating EventType');
          })
          );
        }
        update(eventType: EventType){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/eventTypes/' + eventType.id;
          return this.http.put<any>(this.url, eventType, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating EventType');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/eventTypes/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating EventType');
         })
       );
       }


}


