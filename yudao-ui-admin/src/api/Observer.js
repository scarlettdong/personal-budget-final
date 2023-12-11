
import request from '@/utils/request'

export function getCategoryAndAmount(data){
    return request({
        url:"/Observer/getCategoryAndAmount",
        method:'get'
    })
}
export function getCategoryAndDate(data){
    return request({
        url:"/Observer/getCategorDate",
        method:'get'
    })
}