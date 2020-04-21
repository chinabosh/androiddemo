package com.bosh.module_demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.bosh.module_demo.fileprovider.MyUpdateFileProvider;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import java.io.File;

import butterknife.OnClick;

public class SilentInstallActivity extends BaseActivity {

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_silent_install;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R2.id.tv_open_service, R2.id.tv_install})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_open_service) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        } else if (viewId == R.id.tv_install) {
            // 通过Intent安装APK文件
//            File apkFile = new File("/mnt/sdcard/test.apk");
            File apkFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.apk");
            Log.e("test", "apkFile:" + apkFile.getAbsolutePath());
            Intent i = new Intent(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.e("test", "packageName:" + getPackageName());
                Uri contentUri = MyUpdateFileProvider.getUriForFile(this, this.getPackageName() + ".share", apkFile);
                i.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                i.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            startActivity(i);
        }
    }
}
