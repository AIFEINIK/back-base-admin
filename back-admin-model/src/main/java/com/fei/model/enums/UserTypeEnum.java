package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 用户类型
 */
public enum UserTypeEnum implements TransferEnum<Integer> {

    SYS(0, "系统用户");

    private final Integer value;
    private final String desc;

    UserTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserTypeEnum getType(int value) {
        UserTypeEnum[] values = UserTypeEnum.values();
        for (UserTypeEnum type : values) {
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
