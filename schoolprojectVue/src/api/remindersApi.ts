import request from '@/utils/request'

// 获取用户的所有提醒
export function getReminders(userId) {
  return request({
    url: `http://localhost:8080/reminders/${userId}`,
    method: 'get'
  })
}

// 添加新提醒
export function addReminder(data) {
  console.log("remindersApi 中的 data：",data)
  return request({
    url: 'http://localhost:8080/reminders/add',
    method: 'post',
    data
  })
}

// 更新提醒状态
export function updateReminder(data) {
  return request({
    url: `http://localhost:8080/reminders/update/${data.reminderId}`,
    method: 'put',
    data
  })
}

// 删除提醒
export function deleteReminder(reminderId) {
  return request({
    url: `http://localhost:8080/reminders/delete/${reminderId}`,
    method: 'delete'
  })
}