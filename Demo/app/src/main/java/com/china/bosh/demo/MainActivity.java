package com.china.bosh.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.china.bosh.demo.R;

/**
 * @author bosh
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.app_activity_main2);
        ARouter.getInstance().build("/demo/demoMain").navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Log.i("arouter", "found demo main");
            }

            @Override
            public void onLost(Postcard postcard) {
                Log.i("arouter", "lost demo main");
            }

            @Override
            public void onArrival(Postcard postcard) {
                Log.i("arouter", "跳转到demo main界面");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                Log.i("arouter", "main to demo main interrupt");
            }
        });
        finish();
    }
}
