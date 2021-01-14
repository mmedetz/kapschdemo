export interface Employee {
    id: number;
    name: string;
    email: string;
    entryDate: string;
    department : Department;
}

export interface Department {
    id: number;
    name: string;
}