package com.fei.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ZhangPengFei
 * @description 菜单权限表
 */
@Data
public class SysMenu {
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 路由参数
     */
    private String query;
    /**
     * 是否为外链（0是 1否）
     */
    private Boolean isFrame;
    /**
     * 是否缓存（0不缓存 1缓存）
     */
    private Boolean isCache;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;
    /**
     * 菜单展示状态（0显示 1隐藏）
     */
    private Integer visible;
    /**
     * 菜单状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
}

