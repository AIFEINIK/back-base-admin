package com.fei.model.enums;

/**
 * @author ZhangPengFei
 * @description 菜单类型
 */
public enum MenuTypeEnum implements TransferEnum<String> {

    M("目录"),
    C("菜单"),
    F("按钮");
    private final String desc;

    MenuTypeEnum(String desc) {
        this.desc = desc;
    }

    public static MenuTypeEnum getType(String name) {
        MenuTypeEnum[] values = MenuTypeEnum.values();
        for (MenuTypeEnum type : values) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getValue() {
        return desc;
    }
}
