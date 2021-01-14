import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Employee } from './employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiGatewayURL = "http://localhost:8080/api/employees/";

  constructor(private http: HttpClient) { }

  submit(employee: Employee) : Observable<Employee> {
    console.log('Employee to submit', employee);
    return this.http.post<Employee>(this.apiGatewayURL, employee);
  }

  getAll() : Observable<Array<Employee>>{
    console.log('Retrieving created employee..');
    return this.http.get<Array<Employee>>(this.apiGatewayURL);
  }

  delete(employee: Employee) : Observable<Employee> {
    console.log("Deleting employee " + employee);
    return this.http.delete<Employee>(this.apiGatewayURL + employee.id);
  }
}
