import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Department } from './employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private apiGatewayURL = "http://localhost:8080/api/departments/";

  constructor(private http: HttpClient) { }

  getAll() : Observable<Array<Department>>{
    console.log('Retrieving all departments...');
    return this.http.get<Array<Department>>(this.apiGatewayURL);
  }
}
