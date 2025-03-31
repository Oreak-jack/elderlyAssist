package cn.edu.tute.schoolproject.service.reminders;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Reminders;
import cn.edu.tute.schoolproject.mapper.reminders.RemindersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class RemindersService {
    @Autowired
    private RemindersMapper remindersMapper;

    public ResponseResult<List<Reminders>> getReminders(Integer userId) {
        if (userId == null || userId <= 0) {
            return ResponseResult.error("用户ID不能为空或小于等于0");
        }
        List<Reminders> reminders = remindersMapper.getReminders(userId);
        System.out.println("消息："+reminders);
        return ResponseResult.success("获取提醒列表成功", reminders);
    }

    @Transactional
    public ResponseResult<Integer> addReminder(Reminders reminder) {
        if (reminder == null) {
            return ResponseResult.error("提醒内容不能为空");
        }
        if (reminder.getUserId() == null || reminder.getUserId() <= 0) {
            return ResponseResult.error("用户ID不能为空或小于等于0");
        }
        
        // 验证时间格式
        try {
            if (reminder.getReminderTime() != null) {
                LocalDateTime.parse(reminder.getReminderTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        } catch (DateTimeParseException e) {
            return ResponseResult.error("提醒时间格式不正确，请使用ISO格式(yyyy-MM-ddTHH:mm:ss)");
        }
        
        int result = remindersMapper.addReminder(reminder);
        return ResponseResult.success("添加提醒成功", result);
    }

    @Transactional
    public ResponseResult<Integer> updateReminder(Reminders reminder) {
        if (reminder == null) {
            return ResponseResult.error("提醒内容不能为空");
        }
        if (reminder.getReminderId() == null || reminder.getReminderId() <= 0) {
            return ResponseResult.error("提醒ID不能为空或小于等于0");
        }
        
        // 验证时间格式
        try {
            if (reminder.getReminderTime() != null) {
                LocalDateTime.parse(reminder.getReminderTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        } catch (DateTimeParseException e) {
            return ResponseResult.error("提醒时间格式不正确，请使用ISO格式(yyyy-MM-ddTHH:mm:ss)");
        }
        
        int result = remindersMapper.updateReminder(reminder);
        if (result == 0) {
            return ResponseResult.error("更新失败，提醒不存在");
        }
        return ResponseResult.success("更新提醒成功", result);
    }

    @Transactional
    public ResponseResult<Integer> deleteReminder(Integer reminderId) {
        if (reminderId == null || reminderId <= 0) {
            return ResponseResult.error("提醒ID不能为空或小于等于0");
        }
        int result = remindersMapper.deleteReminder(reminderId);
        if (result == 0) {
            return ResponseResult.error("删除失败，提醒不存在");
        }
        return ResponseResult.success("删除提醒成功", result);
    }
}
