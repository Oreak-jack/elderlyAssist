import request from '@/utils/request'
import type { User } from '@/models/user'
import type { SmartwatchData } from '@/models/healthData'

interface ElderlyWithHealth extends User {
  healthData?: SmartwatchData;
}

interface FamilyResponse {
  code: number;
  message: string;
  data: {
    elderlyList: ElderlyWithHealth[];
  }
}

// 获取家属信息和关联老人列表
export function getFamilyInfo() {
  return request<FamilyResponse>({
    url: '/api/family/elderly-list',
    method: 'get'
  })
}

// 获取可绑定的老人列表
export function getAvailableElderly() {
  return request<{code: number; message: string; data: User[]}>({
    url: '/api/family/available-elderly',
    method: 'get'
  })
}

// 绑定老人
export function bindElderly(elderlyId: number, familyId: number) {
  return request<{code: number; message: string}>({
    url: '/api/family/bind',
    method: 'post',
    data: { 
      elderlyId,
      familyId
    }
  })
}

// 获取指定老人的最新健康数据
export function getElderlyHealthData(elderlyId: number) {
  return request<{code: number; message: string; data: SmartwatchData}>({
    url: `/api/smartwatch/latest/${elderlyId}`,
    method: 'get'
  })
}