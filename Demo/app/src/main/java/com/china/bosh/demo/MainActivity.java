package com.china.bosh.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        Intent intent = getIntent();
        if (intent != null) {
            int type = intent.getIntExtra("intent_type", 0);
            switch (type) {
                default:
                case 0:
                    toDemo();
                    break;
                case 1:
                    toKotlin();
                    break;
                case 2:
                    toMvp();
                    break;
            }
        } else {
            toDemo();
        }
        finish();
    }

    private void toDemo() {
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
    }

    private void toKotlin() {
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

    private void toMvp() {
        ARouter.getInstance().build("/mvp/login").navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Log.i("arouter", "found mvp login");
            }

            @Override
            public void onLost(Postcard postcard) {
                Log.i("arouter", "lost mvp login");
            }

            @Override
            public void onArrival(Postcard postcard) {
                Log.i("arouter", "跳转到 mvp login界面");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                Log.i("arouter", "to mvp login interrupt");
            }
        });
    }
}
