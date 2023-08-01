package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 用户删除标识
 */
public enum UserDelFlagEnum implements TransferEnum<Integer> {

    NORMAL(0, "存在"),

    DELETE(1, "删除");

    private final Integer value;
    private final String desc;

    UserDelFlagEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserDelFlagEnum getType(int value) {
        UserDelFlagEnum[] values = UserDelFlagEnum.values();
        for (UserDelFlagEnum type : values) {
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
