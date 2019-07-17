package com.bosh.mvp_module;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author bosh
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mvp_activity_main);
        ARouter.getInstance().build("/mvp/login").navigation();
        finish();
    }
}
