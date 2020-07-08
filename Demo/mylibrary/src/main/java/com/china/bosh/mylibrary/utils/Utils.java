package com.china.bosh.mylibrary.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lzq
 * @date 2018/11/6
 */

public class Utils {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * view.setId(generateViewid()) 避免id冲突
     * @return
     */
    public static int generateViewId(){
        for(;;){
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if(newValue > 0x00FFFFFF){
                //D大于0x00FFFFFF的已经在xml中定义到了，容易发生冲突。
                newValue = 1;
            }
            if(sNextGeneratedId.compareAndSet(result, newValue)){
                return result;
            }
        }
    }
}
