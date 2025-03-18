export interface Reminder {
    ReminderID: number;
    UserID: number;
    Time: string; // 08:00 格式
    Content: string;
    IconClass?: string; // 阿里 Iconfont class 名称
    Completed: boolean;
    CreatedAt: Date;
    UpdatedAt: Date;
}

