export interface User {
    userId: number;
    userName: string;
    password: string;
    userType: '家属' | '老人';
    phoneNumber?: string;
    email?: string;
    address?: string;
    age?: number | null;
    gender?: '男' | '女' | null;
    createdAt: Date;
}