package com.zhuyx.mytraining.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhuyingxin on 2016/6/15.
 * email : rixtdqqq_2015@163.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
    /**方法规则，即使用get、set方法，字段规则不检查get、set方法 */
    boolean method() default true;
}
