import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { PrivatePost } from '../models/private-post';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PrivatePostService {

  url = environment.baseUrl + 'api/privatePosts';

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



  index(): Observable<PrivatePost[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<PrivatePost[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(privatePostId: number): Observable<PrivatePost> {
      this.url = environment.baseUrl + 'api/privatePosts';
      const httpOptions = this.gethttpOptions();
      return this.http.get<PrivatePost>(`${this.url}/${privatePostId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('PrivatePostService.show(): Error retrieving PrivatePost ' + privatePostId);
        })
        );
      }

      create(privatePost: PrivatePost){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/privatePosts';
        return this.http.post<any>(this.url, privatePost, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating PrivatePost');
          })
          );
        }
        update(privatePost: PrivatePost){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/privatePosts/' + privatePost.id;
          return this.http.put<any>(this.url, privatePost, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating PrivatePost');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/privatePosts/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating PrivatePost');
         })
       );
       }
}
