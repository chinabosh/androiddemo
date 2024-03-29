package com.bosh.module_demo.ui.activity;


import android.content.Intent;
import android.location.Address;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.bosh.module_demo.ui.activity.recyclerview.RecyclerViewActivity;
import com.bosh.module_demo.ui.activity.recyclerview.stickyheader.StickyHeaderActivity;
import com.bosh.module_demo.ui.activity.sms.VerificationActivity;
import com.bosh.module_demo.ui.flutter.FlutterActivity;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;
import com.china.bosh.mylibrary.utils.LocationUtil;
import com.china.bosh.mylibrary.utils.PermissionUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author chinabosh
 */
@Route(path = "/demo/demoMain")
public class MainActivity extends BaseActivity {


    @BindView(R2.id.iv_image)
    ImageView mImTest;

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_main;
    }

    @Override
    protected void initView() {
//        Glide.with(this).load("http://192.168.30.38:8185/interface/open/picture/2019/04/28/6704256120190428.png").into(mImTest);
    }

    @Override
    protected void initData() {
        PermissionUtil.requestPermission(this, new String[]{}, new PermissionUtil.PermissionAction() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied() {

            }
        });
    }

    @OnClick({R2.id.tv_span, R2.id.tv_sms, R2.id.tv_recycler, R2.id.tv_notify, R2.id.tv_flutter,
            R2.id.tv_gauss, R2.id.tv_music, R2.id.tv_sticky_header, R2.id.tv_verification_code,
            R2.id.tv_hook, R2.id.tv_silent_install, R2.id.tv_kotlin})
    public void onClick(@NonNull  View view) {
        int id = view.getId();
        if(id == R.id.tv_span) {
            LocationUtil.getInstance(this).setAddressCallback(new LocationUtil.AddressCallback() {
                @Override
                public void onGetAddress(Address address) {
                    Log.e("test", address.getAdminArea() + address.getLocality());
                }

                @Override
                public void onGetLocation(double lat, double lng) {

                }
            });
//            startActivity(SpannableActivity.class);
        } else if(id == R.id.tv_sms) {
            startActivity(SmsActivity.class);
        } else if(id == R.id.tv_recycler){
            startActivity(RecyclerViewActivity.class);
        } else if(id == R.id.tv_notify) {
            startActivity(NotificationActivity.class);
        } else if(id == R.id.tv_flutter) {
            startActivity(FlutterActivity.class);
        } else if(id == R.id.tv_gauss) {
            startActivity(GaussActivity.class);
        } else if(id == R.id.tv_music){
//            startService(new Intent(this, MusicService.class));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(new Intent(this, MusicService.class));
            }
        } else if(id == R.id.tv_sticky_header){
            startActivity(StickyHeaderActivity.class);
        } else if(id == R.id.tv_verification_code){
            startActivity(VerificationActivity.class);
        } else if(id == R.id.tv_hook) {
            startActivity(HookClickActivity.class);
        } else if (id == R.id.tv_silent_install) {
            startActivity(SilentInstallActivity.class);
        } else if(id == R.id.tv_kotlin) {
            ARouter.getInstance().build("/kotlin/main").navigation(this, new NavigationCallback() {
                @Override
                public void onFound(Postcard postcard) {
                    Log.i("arouter", "found kotlin main");
                }

                @Override
                public void onLost(Postcard postcard) {
                    Log.i("arouter", "lost kotlin main");
                }

                @Override
                public void onArrival(Postcard postcard) {
                    Log.i("arouter", "跳转到 kotlin main界面");
                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    Log.i("arouter", "to kotlin main interrupt");
                }
            });
        }
    }

    /**
     * exception转string，可以将错误写入文件、或者上传
     * @param ex
     */
    private void writeException(Exception ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        try{
            ex.printStackTrace(printWriter);
            String result = writer.toString();
            Log.e("exception", ex.getMessage() + "\n" + result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}