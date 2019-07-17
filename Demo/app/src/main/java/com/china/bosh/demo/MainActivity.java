package com.china.bosh.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.china.bosh.demo.R;

/**
 * @author bosh
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        ARouter.getInstance().build("/demo/demoMain").navigation();
        finish();
    }
}
