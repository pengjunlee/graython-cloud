// import request from '@/utils/http'
// import { BaseResult } from '@/types/axios'
import api from '@/utils/http'
import { BaseResult } from '@/types/axios'
import { UserInfo } from '@/types/store'
import avatar from '@imgs/user/avatar.png'

export class UserService {
  // 模拟登录接口
  static login( data: { username: string; password: string, tenantId?: string } ): Promise<BaseResult> {
    return api.post({
      url: '/admin/login',
      data: data
    })
  }

  // 获取用户信息
  static getUserInfo(): Promise<BaseResult<UserInfo>> {
    return api.get({
      url: '/admin/userinfo'
    })
  }
}
