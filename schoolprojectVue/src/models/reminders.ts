export interface Reminder {
    ReminderId: number;
    UserId: number;
    Time: string; // 08:00 格式
    Content: string;
    IconClass?: string; // 阿里 Iconfont class 名称
    Completed: boolean; // 默认是 false
    Important: boolean;
    CreatedAt: Date;
    UpdatedAt: Date;
}

