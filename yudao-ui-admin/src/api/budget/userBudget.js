import request from '@/utils/request'

// 创建预算
export function createUserBudget(data) {
  return request({
    url: '/budget/user-budget/create',
    method: 'post',
    data: data
  })
}

// 更新预算
export function updateUserBudget(data) {
  return request({
    url: '/budget/user-budget/update',
    method: 'put',
    data: data
  })
}

// 删除预算
export function deleteUserBudget(id) {
  return request({
    url: '/budget/user-budget/delete?id=' + id,
    method: 'delete'
  })
}

// 获得预算
export function getUserBudget(id) {
  return request({
    url: '/budget/user-budget/get?id=' + id,
    method: 'get'
  })
}

// 获得预算分页
export function getUserBudgetPage(query) {
  return request({
    url: '/budget/user-budget/page',
    method: 'get',
    params: query
  })
}

// 导出预算 Excel
export function exportUserBudgetExcel(query) {
  return request({
    url: '/budget/user-budget/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
