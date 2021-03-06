package com.china.bosh.mylibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lzq
 * @date 2018/7/4
 */

/**
 * 如果当前activity 或fragment 需要EventBus，就在类上加上注解
 * 不过activity 要继承{@link com.china.bosh.mylibrary.ui.activity.BaseActivity}
 * fragment 也要继承BaseFragment
 * usage:
 * @BindEventBus
 * public class SomeActivity extends Activity{
 *     //...
 * }
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindEventBus {
}
