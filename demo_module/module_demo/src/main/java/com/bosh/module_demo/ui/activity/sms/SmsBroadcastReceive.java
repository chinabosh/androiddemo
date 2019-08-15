package com.bosh.module_demo.ui.activity.sms;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import com.china.bosh.mylibrary.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzq
 * @date 2019-08-15
 */
public class SmsBroadcastReceive extends BroadcastReceiver {

    public static final String SMS_RECEIVE_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(SMS_RECEIVE_ACTION.equals(intent.getAction())) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for(Object pdu : pdus) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte [])pdu);
                String sender = smsMessage.getDisplayOriginatingAddress();
                //短信内容
                String content = smsMessage.getDisplayMessageBody();
                long date = smsMessage.getTimestampMillis();
                Date tiemDate = new Date(date);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = simpleDateFormat.format(tiemDate);
                Log.e("test", "sender:"+ sender + "\r\ncontent:" + content + "\r\ntime:" + time);
                String verificationCode = StringUtils.getNumFromString(content);
                ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = new ClipData(new ClipDescription("验证码",new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}), new ClipData.Item(verificationCode));
                manager.setPrimaryClip(data);
            }
        }
    }
}
