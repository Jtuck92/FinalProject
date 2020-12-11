import { PrivateEvent } from './../models/private-event';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { pid } from 'process';

@Injectable({
  providedIn: 'root',
})
export class PrivateEventService {
  constructor(private http: HttpClient, private authService: AuthService) {}
  private url = environment.baseUrl + 'api/privateEvents';
  privateEvents: PrivateEvent[] = [];

  index(): Observable<PrivateEvent[]> {
    const httpOptions = this.gettingHttpOptions();
    return this.http.get<PrivateEvent[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

  show(eid: number): Observable<PrivateEvent> {
    const httpOptions = this.gettingHttpOptions();
    return this.http.get<PrivateEvent>(`${this.url}/${eid}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('PrivateEvent.show(): Error retrieving todo ' + pid);
      })
    );
  }

  create(privateEvent: PrivateEvent) {
    console.log(privateEvent);
    const httpOptions = this.gettingHttpOptions();
    this.privateEvents.push(privateEvent);
    return this.http.post<any>(this.url, privateEvent, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error Creating PrivateComment');
      })
    );
  }

  updateTodo(privateEvent: PrivateEvent) {
    console.log(privateEvent);
    const httpOptions = this.gettingHttpOptions();
    this.privateEvents.push(privateEvent);
    return this.http
      .put<any>(this.url + '/' + privateEvent.id, privateEvent, httpOptions)
      .pipe(catchError(this.handleError));
  }

  destroy(id: number) {
    const httpOptions = this.gettingHttpOptions();
    this.url = environment.baseUrl + 'api/events/' + id;
    return this.http.delete<any>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error Creating Event');
      })
    );
  }

  handleError(error: any) {
    console.error('Something Broke');
    return throwError(error.json().error || 'Server Error');
  }

  gettingHttpOptions() {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return httpOptions;
  }
}
