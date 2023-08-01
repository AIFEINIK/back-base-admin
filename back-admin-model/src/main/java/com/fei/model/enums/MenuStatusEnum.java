package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 菜单状态
 */
public enum MenuStatusEnum implements TransferEnum<Integer> {

    NORMAL(0, "正常"),

    DISABLE(1, "停用");

    private final Integer value;
    private final String desc;

    MenuStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static MenuStatusEnum getType(int value) {
        MenuStatusEnum[] values = MenuStatusEnum.values();
        for (MenuStatusEnum type : values) {
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
