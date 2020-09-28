package com.qiu.firechill.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author qiu
 * @create 2020/9/27 16:06
 * @Des 自定义sql的参数注解
 */

@Target(value = {ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlParam {

    String param();
}
