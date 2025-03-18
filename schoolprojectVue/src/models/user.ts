export interface User {
    UserID: number;
    UserName: string;
    Password: string;
    UserType: '老人' | '家属';
    Age?: number;
    Gender?: '男' | '女';
    PhoneNumber?: string;
    Email?: string;
    Address?: string;
    CreatedAt: Date;
}