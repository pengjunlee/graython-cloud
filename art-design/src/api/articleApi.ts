import request from '@/utils/http'
import { PaginationResult, BaseResult } from '@/types/axios'
import { ArticleType, ArticleCategoryType } from './model/articleModel'
import ArticleMockService from './mock/articleApi'

// 文章
class ArticleService {
  // 获取文章列表
  static getArticleList(params: any) {
    const { page, size, searchVal, year } = params
    return request.get<PaginationResult<ArticleType[]>>({
      url: `/api/articles/${page}/${size}?title=${searchVal}&year=${year}`
    })
  }

  // 获取文章类型
  static getArticleTypes(params: object) {
    return request.get<BaseResult<ArticleCategoryType[]>>({
      url: '/api/articles/types',
      params
    })
  }

  // 获取文章详情
  static getArticleDetail(id: number) {
    return request.get<BaseResult<ArticleType>>({
      url: `/api/articles/${id}`
    })
  }

  // 新增文章
  static addArticle(params: any) {
    return request.post<BaseResult>({
      url: '/api/articles/',
      data: params
    })
  }

  // 编辑文章
  static editArticle(id: number, params: any) {
    return request.put<BaseResult>({
      url: `/api/articles/${id}`,
      data: params
    })
  }
}

// 根据环境变量选择数据服务
// console.log(`当前模式: ${import.meta.env.MODE}`)
const useMock = import.meta.env.MODE === 'demonstration'
// console.log(`当前是否演示环境:${useMock}`)

export const ArticleApi = useMock ? ArticleMockService : ArticleService
