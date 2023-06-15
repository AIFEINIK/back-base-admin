package com.fei.common.annotation;

import com.fei.common.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * @author ZhangPengFei
 * @description 多数据源切换
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    /**
     * 切换数据源名称
     */
    DataSourceType value() default DataSourceType.MASTER;
}
