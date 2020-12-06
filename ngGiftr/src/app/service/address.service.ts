import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Address } from '../models/address';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class AddressService {
  url = environment.baseUrl + 'api/addresss';

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



  index(): Observable<Address[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<Address[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(addressId: number): Observable<Address> {
      this.url = environment.baseUrl + 'api/addresses';
      const httpOptions = this.gethttpOptions();
      return this.http.get<Address>(`${this.url}/${addressId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('AddressService.show(): Error retrieving Address ' + addressId);
        })
        );
      }

      create(address: Address){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/addresses';
        return this.http.post<any>(this.url, address, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating Address');
          })
          );
        }
        update(address: Address){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/addresses/' + address.id;
          return this.http.put<any>(this.url, address, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating Address');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/addresses/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating Address');
         })
       );
       }


}
