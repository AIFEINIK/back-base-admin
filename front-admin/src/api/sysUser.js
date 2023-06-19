import request from "@/utils/request";

export function modifyPassword(formData) {
    return request({
        url: '/sysUser/modifySysUserPassword',
        method: 'post',
        data: formData
    })
}