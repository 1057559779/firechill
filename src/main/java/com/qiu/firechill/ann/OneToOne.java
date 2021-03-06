package com.qiu.firechill.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author qiu
 * @create 2020/9/25 11:46
 * @Des 表名
 */

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToOne {

    String pkey();

    String skey();

}
