package cn.edu.tute.schoolproject.controller.elderly;

import cn.edu.tute.schoolproject.common.ResponseResult;
import cn.edu.tute.schoolproject.entity.Reminders;
import cn.edu.tute.schoolproject.service.reminders.RemindersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowCredentials = "true")
public class RemindersController {
    private static final Logger log = LoggerFactory.getLogger(RemindersController.class);
    @Autowired
    private RemindersService remindersService;

    // 获取用户的所有提醒
    @GetMapping("/{userId}")
    public ResponseResult<List<Reminders>> getReminders(@PathVariable Integer userId) {
        return remindersService.getReminders(userId);
    }

    // 添加新提醒
    @PostMapping("/add")
    public ResponseResult<Integer> addReminder(@RequestBody Reminders reminder) {
        log.info("addReminder reminder: {}", reminder);
        return remindersService.addReminder(reminder);
    }

    // 更新提醒状态
    @PutMapping("/update/{reminderId}")
    public ResponseResult<Integer> updateReminder(@PathVariable Integer reminderId, @RequestBody Reminders reminder) {
        reminder.setReminderId(reminderId);
        return remindersService.updateReminder(reminder);
    }

    // 删除提醒
    @DeleteMapping("/delete/{reminderId}")
    public ResponseResult<Integer> deleteReminder(@PathVariable Integer reminderId) {
        return remindersService.deleteReminder(reminderId);
    }
}
