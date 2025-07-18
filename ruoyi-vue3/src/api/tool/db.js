import request from '@/utils/request'

// 查询代码生成数据库列表
export function listDb(query) {
  return request({
    url: '/code/gen/db/list',
    method: 'get',
    params: query
  })
}

// 查询代码生成数据库详细
export function getDb(dbId) {
  return request({
    url: '/code/gen/db/' + dbId,
    method: 'get'
  })
}

// 新增代码生成数据库
export function addDb(data) {
  return request({
    url: '/code/gen/db',
    method: 'post',
    data: data
  })
}

// 修改代码生成数据库
export function updateDb(data) {
  return request({
    url: '/code/gen/db',
    method: 'put',
    data: data
  })
}

// 删除代码生成数据库
export function delDb(dbId) {
  return request({
    url: '/code/gen/db/' + dbId,
    method: 'delete'
  })
}
