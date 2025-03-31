package cn.edu.tute.schoolproject.mapper.reminders;

import cn.edu.tute.schoolproject.entity.Reminders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RemindersMapper {
    // 获取用户的所有提醒
    List<Reminders> getReminders(@Param("userId") Integer userId);

    // 添加新提醒
    int addReminder(Reminders reminder);

    // 更新提醒
    int updateReminder(Reminders reminder);

    // 删除提醒
    int deleteReminder(@Param("reminderId") Integer reminderId);
}
