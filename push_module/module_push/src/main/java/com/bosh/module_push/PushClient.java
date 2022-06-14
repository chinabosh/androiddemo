package com.bosh.module_push;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;

import cn.jpush.android.api.JPushInterface;

/**
 * @author lzq
 * @date 2020/7/8
 */
@Route(path = "/push/client")
public class PushClient  implements IProvider {
    @Autowired(name = "demo/push")
    public PushExportService demoPushService;

    public void initPush(Context context) {
        JPushInterface.init(context);
    }

    @Override
    public void init(Context context) {

    }
}
