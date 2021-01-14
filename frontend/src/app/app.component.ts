import { Component, OnInit } from '@angular/core';
//import { Employee, Department } from '';
//import { ProductService } from '../../service/productservice';
import { SelectItem } from 'primeng/api';
import { Department, Employee } from './employee';
import { EmployeeService } from './employee.service';
import { DepartmentService } from './department.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  //providers: [MessageService],
  styles: [`
        :host ::ng-deep .p-cell-editing {
            padding-top: 0 !important;
            padding-bottom: 0 !important;
        }
    `]
})

export class AppComponent implements OnInit {
  
  employees: Employee[] = [];
  departments: Department[] = [];

  items: SelectItem[] = [];

  clonedProducts: Map<number, Employee> = new Map();

  constructor(private employeeService: EmployeeService, private departmentService: DepartmentService) { }

  ngOnInit() {
    
    this.employeeService.getAll().subscribe(myEmplyees => {
      this.employees = myEmplyees
      console.log(myEmplyees);
    });
    this.departmentService.getAll().pipe().subscribe(myDepartments => {
      this.departments = myDepartments
      myDepartments.map(myDepartment => this.items.push({label: myDepartment.name, value: myDepartment}))
    });

     /* this.employees = [
        new Employee(1, "Test1", "1@1.com", "mydate", new Department(1, "Dep1")),
        new Employee(2, "Test2", "2@1.com", "mydate", new Department(1, "Dep1")),
        new Employee(3, "Test3", "3@1.com", "mydate", new Department(2, "Dep2")),
      ]*/
      //this.statuses = [{label: 'In Stock', value: 'INSTOCK'},{label: 'Low Stock', value: 'LOWSTOCK'},{label: 'Out of Stock', value: 'OUTOFSTOCK'}]
  }

  onRowEditInit(employee: Employee) {
      this.clonedProducts.set(employee.id, { ...employee});
  }

  onRowEditSave(employee: Employee, index: number) {
      this.employeeService.submit(employee).pipe().subscribe(myEmployee => {
        this.clonedProducts.delete(employee.id);
      },error => {
        console.log("Error reverting field!")
        this.onRowEditCancel(employee, index)
      })
  }

  onRowEditCancel(employee: Employee, index: number) {
      
    //console.log('index: ' + index);
    //console.log('employee: ' + employee.name);
    //console.log('employees[index]: ' + this.employees[index].name);
    //console.log('clonedProducts.get(employee.id): ' + this.clonedProducts.get(employee.id)?.name);

    this.employees[index] = this.clonedProducts.get(employee.id)!;
    this.clonedProducts.delete(employee.id);
  }
}