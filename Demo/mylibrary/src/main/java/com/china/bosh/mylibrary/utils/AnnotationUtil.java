package com.china.bosh.mylibrary.utils;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * {@link <a herf=https://www.jianshu.com/p/7454a933dcaf>}
 * @author lzq
 * @date 2019/4/24
 */
public class AnnotationUtil {
    public static void inject(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
//        for(Field field : fields) {
//            boolean needInject = field.isAnnotationPresent(InjectView.class);
//            if (needInject) {
//                InjectView anno = field.getAnnotation(InjectView.class);
//                int id = anno.id();
//                if (id == -1) continue;
//                View view = activity.findViewById(id);
//                Class fieldType = field.getType();
//                try {
//                    //把View转换成field声明的类型
//                    field.set(activity, fieldType.cast(view));
//                } catch (Exception e) {
//                    Log.e(InjectView.class.getSimpleName(), e.getMessage());
//                }
//            }
//        }
    }
}
