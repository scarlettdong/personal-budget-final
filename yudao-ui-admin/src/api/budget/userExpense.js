import request from '@/utils/request'
// 创建支出
export function createUserExpense(data) {
  return request({
    url: '/budget/user-expense/create',
    method: 'post',
    data: data
  })
}

// 更新支出
export function updateUserExpense(data) {
  return request({
    url: '/budget/user-expense/update',
    method: 'put',
    data: data
  })
}

// 删除支出
export function deleteUserExpense(id) {
  return request({
    url: '/budget/user-expense/delete?id=' + id,
    method: 'delete'
  })
}

// 获得支出
export function getUserExpense(id) {
  return request({
    url: '/budget/user-expense/get?id=' + id,
    method: 'get'
  })
}

// 获得支出分页
export function getUserExpensePage(query) {
  return request({
    url: '/budget/user-expense/page',
    method: 'get',
    params: query
  })
}

// 导出支出 Excel
export function exportUserExpenseExcel(query) {
  return request({
    url: '/budget/user-expense/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
