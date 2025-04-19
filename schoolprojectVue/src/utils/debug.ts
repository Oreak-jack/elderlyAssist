export const debugUserData = (data: any) => {
  console.group('用户数据调试')
  console.log('原始数据:', data)
  console.log('UserID类型:', typeof data.UserID, '值:', data.UserID)
  console.log('UserName类型:', typeof data.UserName, '值:', data.UserName)
  console.log('UserType类型:', typeof data.UserType, '值:', data.UserType)
  console.groupEnd()
} 