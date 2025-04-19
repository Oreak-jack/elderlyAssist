import request from '@/utils/request'

export function getReminders(userId) {
  return request({
    url: `/api/reminders/${userId}`,
    method: 'get'
  })
}

export function addReminder(data) {
  return request({
    url: '/api/reminders',
    method: 'post',
    data
  })
}

export function deleteReminder(reminderId) {
  return request({
    url: `/api/reminders/${reminderId}`,
    method: 'delete'
  })
}

export function updateReminder(data) {
  return request({
    url: `/api/reminders/${data.reminderId}`,
    method: 'put',
    data
  })
} 