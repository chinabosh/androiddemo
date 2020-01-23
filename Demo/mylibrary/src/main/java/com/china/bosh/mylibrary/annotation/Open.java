package com.china.bosh.mylibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lzq
 * @date 2019-12-19
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Open {

}
