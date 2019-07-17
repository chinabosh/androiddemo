package com.bosh.module_mvp.ui.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bosh.module_mvp.injector.components.ActivityComponent;
import com.bosh.module_mvp.injector.components.DaggerActivityComponent;
import com.bosh.module_mvp.injector.module.ActivityModule;
import com.bosh.module_mvp.interfaces.IView;
import com.china.bosh.mylibrary.annotation.BindEventBus;
import com.china.bosh.mylibrary.application.BaseApplication;
import com.china.bosh.mylibrary.entity.DataEvent;
import com.china.bosh.mylibrary.utils.PermissionsManager;
import com.china.bosh.mylibrary.utils.PermissionsResultAction;
import com.china.bosh.mylibrary.utils.ToastUtil;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author bosh
 * @date 2019-07-15
 */
public abstract class BaseActivity<P extends BasePresenter> extends FragmentActivity implements IView {

    private static float mNoncompatDensity;
    private static float mNoncompatScaledDensity;
    private MaterialDialog progressDialog;

    @Inject
    protected P mPresenter;

    public BaseActivity() {
    }

    /**
     * get resource id
     * @return return resource id.
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * dagger依赖注入
     * @param component
     */
    protected abstract void initInject(ActivityComponent component);

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == 26 && this.isTranslucentOrFloating()) {
            this.fixOrientation();
        }

        super.onCreate(savedInstanceState);
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }

        this.setContentView(this.attachLayoutRes());
        ButterKnife.bind(this);
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
            }

            @Override
            public void onDenied(String permission) {
            }
        });
        initInject(DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build());
        mPresenter.setLifecycleOwner(this);
        mPresenter.attachView(this);
        getLifecycle().addObserver(mPresenter);
        initView();
        initData();
        setCustomDensity(this, BaseApplication.instance);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle(){
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this));
    }

    public void startActivity(Class targetClass) {
        Intent intent = new Intent(this, targetClass);
        this.startActivity(intent);
    }

    @Override
    public void showLoading() {
        showLoading("请稍候...");
    }

    @Override
    public void showLoading(final String content) {
        Observable.just("")
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(s -> {
                    if (progressDialog == null || !progressDialog.isShowing()) {
                        progressDialog = new MaterialDialog.Builder(this)
                                .content(content)
                                .contentGravity(GravityEnum.CENTER)
                                .cancelable(false)
                                .canceledOnTouchOutside(false)
                                .progressIndeterminateStyle(false)
                                .progress(true, 0)
                                .show();
                    }
                });
    }

    @Override
    public void hideLoading() {
        Observable.just("")
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(s -> {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void toast(@StringRes int id) {
        ToastUtil.showShort(id);
    }

    @Override
    public void toast(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    public void toastLong(@StringRes int id) {
        ToastUtil.showLong(id);
    }

    @Override
    public void toastLong(String msg) {
        ToastUtil.showLong(msg);
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onMainEvent(DataEvent event) {
        this.onEvent(event);
    }

    /**
     * 如果收不到event，请检查是否用了{@link BindEventBus}
     * @param event
     */
    public void onEvent(DataEvent event) {
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT != 26 || !this.isTranslucentOrFloating()) {
            super.setRequestedOrientation(requestedOrientation);
        }
    }

    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;

        try {
            int[] styleableRes = (int[])((int[])Class.forName("com.android.internal.R$styleable").getField("Window").get((Object)null));
            TypedArray ta = this.obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (Boolean)m.invoke((Object)null, ta);
            m.setAccessible(false);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return isTranslucentOrFloating;
    }

    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo)field.get(this);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    private static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (mNoncompatDensity == 0.0F) {
            mNoncompatDensity = appDisplayMetrics.density;
            mNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0.0F) {
                        mNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }

                }

                @Override
                public void onLowMemory() {
                }
            });
        }

        float targetDensity = (float)(appDisplayMetrics.widthPixels / 360);
        float targetScaledDensity = targetDensity * (mNoncompatScaledDensity / mNoncompatDensity);
        int targetDensityDpi = (int)(targetScaledDensity * 160.0F);
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
