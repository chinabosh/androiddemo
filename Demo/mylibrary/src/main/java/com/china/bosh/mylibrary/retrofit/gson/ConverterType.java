package com.china.bosh.mylibrary.retrofit.gson;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

import retrofit2.Retrofit;

/**
 * 动态指定返回的数据对应的entity，
 * 注解接收的entity会在{@link MyGsonConverterFactory#responseBodyConverter(Type, Annotation[], Retrofit)}中处理
 * eg：  @ConverterType(entity = Test.class, map = Test1.class)
 *      @Post("someUrl")
 *      Observable<ResponseT> getSome();
 *
 * @author lzq
 * @date 2021/7/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConverterType {
    Class<?> entity() default Object.class;
    Class<?> map() default Object.class;
    Class<?> qvo() default Object.class;
    Class<?> vo() default Object.class;
    Class<?> rows() default Object.class;

}
