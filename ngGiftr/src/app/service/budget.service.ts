import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Budget } from '../models/budget';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

    url = environment.baseUrl + 'api/budgets';

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



  index(): Observable<Budget[]> {
    const httpOptions = this.gethttpOptions();
    return this.http.get<Budget[]>(this.url + '?sorted=true', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }

    show(budgetId: number): Observable<Budget> {
      this.url = environment.baseUrl + 'api/budgets';
      const httpOptions = this.gethttpOptions();
      return this.http.get<Budget>(`${this.url}/${budgetId}`, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('BudgetService.show(): Error retrieving Budget ' + budgetId);
        })
        );
      }

      create(budget: Budget){
        const httpOptions = this.gethttpOptions();
        this.url = environment.baseUrl + 'api/budgets';
        return this.http.post<any>(this.url, budget, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error Creating Budget');
          })
          );
        }
        update(budget: Budget){
          const httpOptions = this.gethttpOptions();
          this.url = environment.baseUrl + 'api/budgets/' + budget.id;
          return this.http.put<any>(this.url, budget, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('Error Updating Budget');
            })
            );


          }

          destroy(id: Number){
            const httpOptions = this.gethttpOptions();
      this.url = environment.baseUrl + 'api/budgets/' + id;
       return this.http.delete<any>(this.url, httpOptions)
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError('Error Creating budget');
         })
       );
       }


}
