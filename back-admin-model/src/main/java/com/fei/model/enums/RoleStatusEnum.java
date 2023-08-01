package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 角色状态
 */
public enum RoleStatusEnum implements TransferEnum<Integer> {

    NORMAL(0, "正常"),

    DISABLE(1, "禁用");

    private final Integer value;
    private final String desc;

    RoleStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RoleStatusEnum getType(int value) {
        RoleStatusEnum[] values = RoleStatusEnum.values();
        for (RoleStatusEnum type : values) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
