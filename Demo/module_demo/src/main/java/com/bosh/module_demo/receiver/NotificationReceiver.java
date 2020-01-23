package com.bosh.module_demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;


import com.china.bosh.mylibrary.utils.ToastUtil;

/**
 * @author bosh
 */
@androidx.annotation.RestrictTo(androidx.annotation.RestrictTo.Scope.LIBRARY)
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(TextUtils.isEmpty(intent.getAction())){
            return;
        }
        switch (intent.getAction()) {
            case "yes":
                Log.e("Test", "notification yes press");
                ToastUtil.showShort("yes action");
                break;
            case "no":
                Log.e("Test", "notification no press");
                ToastUtil.showShort("no action");
                break;
            default:
        }
    }
}
