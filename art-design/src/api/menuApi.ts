import { fourDotsSpinnerSvg } from '@/assets/svg/loading'
import { asyncRoutes } from '@/router/modules/asyncRoutes'
import request from '@/utils/http'
import { MenuListType } from '@/types/menu'
import { processRoute } from '@/utils/menu'
import { ElLoading } from 'element-plus'
import { MenuMockService } from './mock/menuApi'
import { BaseResult } from '@/types/axios'

// 菜单接口
const MenuService = {
  // 获取菜单列表，请求后端接口
  async getMenuList(
    delay: number = 300
  ): Promise<{ menuList: MenuListType[]; closeLoading: () => void }> {
    const loading = ElLoading.service({
      lock: true,
      background: 'rgba(0, 0, 0, 0)',
      svg: fourDotsSpinnerSvg,
      svgViewBox: '0 0 40 40'
    })

    try {
      // 请求后端菜单接口
      const response = await request.get<BaseResult<any[]>>({
        url: '/api/menu/list', // 后端菜单接口地址
        timeout: 10000
      })

      // 处理后端返回的菜单数据
      const menuList = response.data || []
      const processedMenuList: MenuListType[] = menuList.map((route) => processRoute(route))

      // 模拟延迟（可选）
      if (delay > 0) {
        await new Promise(resolve => setTimeout(resolve, delay))
      }

      return {
        menuList: processedMenuList,
        closeLoading: () => loading.close()
      }
    } catch (error) {
      console.error('获取菜单数据失败:', error)
      loading.close()
      
      // 错误时返回空菜单或默认菜单
      return {
        menuList: [],
        closeLoading: () => {}
      }
    }
  }
}

// 根据环境变量选择数据服务
const useMock = import.meta.env.MODE === 'demonstration' || import.meta.env.VITE_USE_MOCK_MENU === 'true'

export default useMock ? MenuMockService : MenuService
