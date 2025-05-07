import { request, type RequestConfig } from '@/utils/request.ts'
import { useUserStore } from '@/stores/userStore'
import type { User } from '@/models/user'
import type { SmartwatchData } from '@/models/healthData'
import type { AxiosResponse } from 'axios'

interface ElderlyWithHealth extends User {
  healthData?: SmartwatchData;
}

interface FamilyResponse {
  elderlyList: ElderlyWithHealth[];
}

// 获取家属信息和关联老人列表
export function getFamilyInfo(): Promise<AxiosResponse<FamilyResponse>> {
  const userStore = useUserStore()
  const familyId = userStore.user?.userId
  if (!familyId) {
    return Promise.reject(new Error('未获取到家属ID'))
  }
  
  const config: RequestConfig = {
    url: `/api/family/list/${familyId}`,
    method: 'get'
  }
  return request<FamilyResponse>(config)
}

// 获取可绑定的老人列表
export function getAvailableElderly(): Promise<AxiosResponse<User[]>> {
  const userStore = useUserStore()
  console.log("确保数据传输进来了",userStore.user)
  // const familyId = userStore.user?.userId
  // if (!familyId) {
  //   return Promise.reject(new Error('未获取到家属ID'))
  // }
  
  const config: RequestConfig = {
    url: `/api/family/available-elderly`,
    method: 'get'
  }
  return request<User[]>(config)
}

// 绑定老人
export function bindElderly(elderlyId: number, familyId: number): Promise<AxiosResponse<any>> {
  const config: RequestConfig = {
    url: '/api/family/bind',
    method: 'post',
    data: { 
      elderlyId,
      familyId
    }
  }
  return request(config)
}

// 获取指定老人的最新健康数据
export function getElderlyHealthData(elderlyId: number): Promise<AxiosResponse<SmartwatchData>> {
  const config: RequestConfig = {
    url: `/api/health/latest/${elderlyId}`,
    method: 'get'
  }
  return request<SmartwatchData>(config)
}