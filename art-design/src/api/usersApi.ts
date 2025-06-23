import request from '@/utils/http'
import { BaseResult } from '@/types/axios'
import UserMockService from './mock/usersApi'

class UserService {
  // 模拟登录接口
  static login(params: { username: string; password: string }) {
    return request.post<BaseResult>({
      url: '/user/login',
      data: params
    })
  }

  // 获取用户信息
  static getUserInfo() {
    return request.get<BaseResult>({
      url: '/user/info'
    })
  }
}

// 根据环境变量选择数据服务
// console.log(`当前模式: ${import.meta.env.MODE}`)
const useMock = import.meta.env.MODE === 'demonstration'
// console.log(`当前是否演示环境:${useMock}`)

export default useMock ? UserMockService : UserService
