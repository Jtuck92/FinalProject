import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Payment } from '../models/payment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  url = environment.baseUrl + 'api/payments';

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



  index(): Observable<Payment[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<Payment[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(paymentId: number): Observable<Payment> {
      this.url = environment.baseUrl + 'api/payments';
      const httpOptions = this.gethttpOptions();
      return this.http.get<Payment>(`${this.url}/${paymentId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('PaymentService.show(): Error retrieving Payment ' + paymentId);
        })
        );
      }

      create(payment: Payment){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/payments';
        return this.http.post<any>(this.url, payment, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating Payment');
          })
          );
        }
        update(payment: Payment){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/payments/' + payment.id;
          return this.http.put<any>(this.url, payment, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating Payment');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/payments/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating Payment');
         })
       );
       }


}
