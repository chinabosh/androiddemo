package com.china.bosh.demo.ui.activity;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.china.bosh.demo.R;
import com.china.bosh.mylibrary.entity.DataEvent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author chinabosh
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.iv_image)
    ImageView mImTest;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        Glide.with(this).load("http://192.168.30.38:8185/interface/open/picture/2018/11/06/4913813220181106.png").into(mImTest);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_span, R.id.tv_sms, R.id.tv_recycler, R.id.tv_notify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_span:
//                startActivity(SpannableActivity.class);
                DataEvent event = null;
                try {
                    event.getErrorCode();
                } catch (Exception e) {
                    writeException(e);
                }

                break;
            case R.id.tv_sms:
                startActivity(SmsActivity.class);
                break;
            case R.id.tv_recycler:
                startActivity(RecyclerViewActivity.class);
                break;
            case R.id.tv_notify:
                startActivity(NotificationActivity.class);
                break;
            default:
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