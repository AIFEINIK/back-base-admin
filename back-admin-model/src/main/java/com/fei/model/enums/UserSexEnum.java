package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 用户性别
 */
public enum UserSexEnum implements TransferEnum<Integer> {

    UNKNOWN(0, "未知"),

    M(1, "男"),
    G(2, "女");

    private final Integer value;
    private final String desc;

    UserSexEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserSexEnum getType(int value) {
        UserSexEnum[] values = UserSexEnum.values();
        for (UserSexEnum type : values) {
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
