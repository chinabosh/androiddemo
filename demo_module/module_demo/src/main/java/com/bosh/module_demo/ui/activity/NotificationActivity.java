package com.bosh.module_demo.ui.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.bosh.module_demo.receiver.NotificationReceiver;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;
import com.china.bosh.mylibrary.utils.NotificationChannels;

import androidx.core.app.NotificationCompat;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @see <a href = "https://www.jianshu.com/p/6aec3656e274"/>
 * @author bosh
 * @date 2019/4/24
 */

public class NotificationActivity extends BaseActivity {

    @BindView(R2.id.tv_notify_normal)
    TextView mTvShowNotification;

    private NotificationManager mNotificationManager;
    private final int NOTIFICATION_ID_NORMAL = 0;
    private final int NOTIFICATION_ID_ACTION = 1;
    private final int NOTIFICATION_ID_CRITICAL = 2;

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_notification;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void showNotificationNormal(){
        int icon = R.drawable.demo_huaji;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, NotificationChannels.DEFAULT);
            builder.setContentTitle("通知测试")
                    .setContentText("android 8.0以上通知")
                    //设置点击通知后自动删除通知
                    .setAutoCancel(true)
                    //设置显示通知时间
                    .setShowWhen(true)
                    //设置通知右侧大图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.demo_huaji))
                    .setSmallIcon(icon);
            PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
            //设置点击通知相应事件
            builder.setContentIntent(pendingintent);
            mNotificationManager.notify(NOTIFICATION_ID_NORMAL, builder.build());
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("通知测试")
                    .setContentText("android 8.0以下通知")
                    .setAutoCancel(true)
                    .setShowWhen(true)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.demo_huaji))
                    .setSmallIcon(icon);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(pendingIntent);
            mNotificationManager.notify(NOTIFICATION_ID_NORMAL, builder.build());
        }
    }

    /**
     * api 23 以上才支持
     */
    @TargetApi(23)
    private void showNotificationAction(){
        int icon = R.drawable.demo_huaji;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, NotificationChannels.IMPORTANCE);
            builder.setSmallIcon(icon)
                    .setContentTitle("通知处理")
                    .setContentText("是否处理？");
            Intent yesIntent = new Intent(this, NotificationReceiver.class);
            yesIntent.setAction("yes");
            PendingIntent yesPendingIntent = PendingIntent.getBroadcast(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Action yesAction = new Notification.Action.Builder(
                    Icon.createWithResource(this, icon), "yes", yesPendingIntent
            ).build();
            Intent noIntent = new Intent(this, NotificationReceiver.class);
            noIntent.setAction("no");
            PendingIntent noPendingIntent = PendingIntent.getBroadcast(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Action noAction = new Notification.Action.Builder(
                    Icon.createWithResource(this, icon), "no", noPendingIntent
            ).build();
            builder.setActions(yesAction, noAction);
            mNotificationManager.notify(NOTIFICATION_ID_ACTION, builder.build());
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(icon)
                    .setContentTitle("通知处理")
                    .setContentText("是否处理");
            Intent yesIntent = new Intent(this, NotificationReceiver.class);
            yesIntent.setAction("yes");
            PendingIntent yesPendingIntent = PendingIntent.getBroadcast(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action yesAction = new NotificationCompat.Action(R.drawable.demo_huaji, "yes", yesPendingIntent);
            Intent noIntent = new Intent(this, NotificationReceiver.class);
            noIntent.setAction("no");
            PendingIntent noPendingIntent = PendingIntent.getBroadcast(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action noAction = new NotificationCompat.Action(
                    R.drawable.demo_huaji, "no", noPendingIntent
            );
            builder.addAction(yesAction)
                    .addAction(noAction);
            mNotificationManager.notify(NOTIFICATION_ID_ACTION, builder.build());
        }
    }

    private void showNotificationCritical(){
        int icon = R.drawable.demo_huaji;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(icon);
            builder.setContentTitle("重要通知")
                    .setAutoCancel(true)
                    .setTicker("123")
                    .setContentText("你的钱被偷了")
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setFullScreenIntent(pendingIntent, true);
            builder.setPriority(NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.notify(NOTIFICATION_ID_CRITICAL, builder.build());
        }
    }

    private void closeNotification(int id){
        mNotificationManager.cancel(id);
    }

    @OnClick({R2.id.tv_notify_normal, R2.id.tv_close_normal, R2.id.tv_notify_action, R2.id.tv_close_action,
    R2.id.tv_notify_critical, R2.id.tv_close_critical})
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_notify_normal) {
            showNotificationNormal();
        } else if(id == R.id.tv_close_normal) {
            closeNotification(NOTIFICATION_ID_NORMAL);
        } else if(id == R.id.tv_notify_action) {
            showNotificationAction();
        } else if(id == R.id.tv_close_action) {
            closeNotification(NOTIFICATION_ID_ACTION);
        } else if(id == R.id.tv_notify_critical) {
            showNotificationCritical();
        } else if(id == R.id.tv_close_critical) {
            closeNotification(NOTIFICATION_ID_CRITICAL);
        } else {

        }
    }
}
