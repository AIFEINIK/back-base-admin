package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 菜单显示状态
 */
public enum MenuVisableEnum implements TransferEnum<Integer> {

    NORMAL(0, "显示"),

    DISABLE(1, "隐藏");

    private final Integer value;
    private final String desc;

    MenuVisableEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static MenuVisableEnum getType(int value) {
        MenuVisableEnum[] values = MenuVisableEnum.values();
        for (MenuVisableEnum type : values) {
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
