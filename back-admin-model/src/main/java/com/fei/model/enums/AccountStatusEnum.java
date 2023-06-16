package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 用户账号状态
 */
public enum AccountStatusEnum implements TransferEnum<Integer> {

    NORMAL(1, "正常"),

    DISABLE(2, "停用");

    private final Integer value;
    private final String desc;

    AccountStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AccountStatusEnum getType(int value) {
        AccountStatusEnum[] values = AccountStatusEnum.values();
        for (AccountStatusEnum type : values) {
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
