package com.bosh.module_demo.ui.activity.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import butterknife.BindView;

/**
 * @author bosh
 */
public class VerificationActivity extends BaseActivity {

    @BindView(R2.id.et_verification_code)
    EditText mEtVerificationCode;

    private SmsBroadcastReceive mReceive;

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_verification;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mReceive = new SmsBroadcastReceive();
        IntentFilter intentFilter = new IntentFilter(SmsBroadcastReceive.SMS_RECEIVE_ACTION);
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mReceive, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mReceive != null) {
            unregisterReceiver(mReceive);
        }
    }
}
