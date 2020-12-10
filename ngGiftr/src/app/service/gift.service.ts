import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Gift } from '../models/gift';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GiftService {

  url = environment.baseUrl + 'api/gifts';

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



  index(): Observable<Gift[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<Gift[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(giftId: number): Observable<Gift> {
      this.url = environment.baseUrl + 'api/gifts';
      const httpOptions = this.gethttpOptions();
      return this.http.get<Gift>(`${this.url}/${giftId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('GiftService.show(): Error retrieving Gift ' + giftId);
        })
        );
      }

      create(gift: Gift){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/gifts';
        return this.http.post<any>(this.url, gift, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating Gift');
          })
          );
        }
        update(gift: Gift){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/gifts/' + gift.id;
          return this.http.put<any>(this.url, gift, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating Gift');
            })
            );


          }

          destroy(id: number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/gifts/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating Gift');
         })
       );
       }


}
