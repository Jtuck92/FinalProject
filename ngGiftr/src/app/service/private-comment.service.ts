import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { PrivateComment } from '../models/private-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PrivateCommentService {
  url = environment.baseUrl + 'api/privateComments';

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
constructor(private http: HttpClient,private auth: AuthService) { }



  index(): Observable<PrivateComment[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<PrivateComment[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(privateCommentId: number): Observable<PrivateComment> {
      this.url = environment.baseUrl + 'api/privateComments';
      const httpOptions = this.gethttpOptions();
      return this.http.get<PrivateComment>(`${this.url}/${privateCommentId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('PrivateCommentService.show(): Error retrieving PrivateComment ' + privateCommentId);
        })
        );
      }

      create(privateComment: PrivateComment){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/privateComments';
        return this.http.post<any>(this.url, privateComment, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating PrivateComment');
          })
          );
        }
        update(privateComment: PrivateComment){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/privateComments/' + privateComment.id;
          return this.http.put<any>(this.url, privateComment, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating PrivateComment');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/privateComments/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating PrivateComment');
         })
       );
       }
}
