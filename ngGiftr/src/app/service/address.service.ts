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

  constructor(private http: HttpClient, private auth: AuthService) {}

  url = environment.baseUrl + 'api/addresses';


  index(): Observable<Address[]> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Address[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }


   getHttpOptions(){
    const  credentials = this.auth.getCredentials();
    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${credentials}`,
        })
      };
}

}
