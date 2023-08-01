package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 用户账号状态
 */
public enum UserStatusEnum implements TransferEnum<Integer> {

    NORMAL(0, "正常"),

    DISABLE(1, "停用");

    private final Integer value;
    private final String desc;

    UserStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserStatusEnum getType(int value) {
        UserStatusEnum[] values = UserStatusEnum.values();
        for (UserStatusEnum type : values) {
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
