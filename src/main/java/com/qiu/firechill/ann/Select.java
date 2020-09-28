package com.qiu.firechill.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author VULCAN
 * @create 2020/9/26 12:29
 */

@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {
    //sql
    String sql();
    //返回类型
    Class<?> result();
}
