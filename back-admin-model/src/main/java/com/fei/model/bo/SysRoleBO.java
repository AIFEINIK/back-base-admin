package com.fei.model.bo;

import com.fei.model.enums.RoleStatusEnum;
import lombok.Data;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class SysRoleBO {

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限
     */
    private String roleKey;
    /**
     * 角色排序
     */
    private Integer roleSort;
    /**
     * 角色状态
     */
    private RoleStatusEnum status;

}
