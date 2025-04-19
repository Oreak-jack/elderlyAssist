export interface User {
    UserID: number;
    UserName: string;
    Password: string;
    UserType: '家属' | '老人';
    PhoneNumber?: string;
    Email?: string;
    Address?: string;
    Age?: number | null;
    Gender?: '男' | '女' | null;
    CreatedAt: Date;
}