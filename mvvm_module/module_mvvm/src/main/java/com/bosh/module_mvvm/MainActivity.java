package com.bosh.module_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bosh.module_mvvm.bean.User;
import com.bosh.module_mvvm.databinding.MvvmActivityMainBinding;

/**
 * @author bosh
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvvmActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.mvvm_activity_main);
        User user = new User();
        user.setUsername("lai");
        user.setPassword("a");
        binding.setUserInfo(user);
    }
}
