package com.fei.interfaces.response;

import com.fei.common.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class GetRouterResponse implements Serializable {

    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 路由参数：如 {"id": 1, "name": "ry"}
     */
    private String query;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private Meta meta;

    /**
     * 子路由
     */
    private List<GetRouterResponse> children;

    @Data
    public static class Meta {
        /**
         * 设置该路由在侧边栏和面包屑中展示的名字
         */
        private String title;

        /**
         * 设置该路由的图标，对应路径src/assets/icons/svg
         */
        private String icon;

        /**
         * 设置为true，则不会被 <keep-alive>缓存
         */
        private boolean noCache;

        /**
         * 内链地址（http(s)://开头）
         */
        private String link;

        public Meta() {
        }

        public Meta(String title, String icon, boolean noCache, String link) {
            this.title = title;
            this.icon = icon;
            this.noCache = noCache;
            if (StringUtils.startsWithAny(link, CommonConstants.HTTP, CommonConstants.HTTPS)) {
                this.link = link;
            }
        }

        public Meta(String title, String icon) {
            this.title = title;
            this.icon = icon;
        }

        public Meta(String title, String icon, String link) {
            this.title = title;
            this.icon = icon;
            this.link = link;
        }
    }

}
