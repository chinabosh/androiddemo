package com.china.bosh.mylibrary.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.annotation.RequiresApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * android 7.0后android.net.conn.CONNECTIVITY_CHANGE静态注册将接收不到广播，只能使用动态注册或者以下官方推荐方式
 * if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
 *             NetworkCallbackImpl networkCallback = new NetworkCallbackImpl();
 *             NetworkRequest.Builder builder = new NetworkRequest.Builder();
 *             NetworkRequest networkRequest = builder.build();
 *             ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
 *             if (connectivityManager != null) {
 *                 connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
 *             }
 *         }
 * 当数据流量开着的时候打开wifi，调用顺序可能为onAvailable -> onLost
 * @author bosh
 * @date 2021/5/10
 */
@RequiresApi(21)
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {

    private PublishSubject<Long> publishSubject;
    private boolean isAvailable;

    public NetworkCallbackImpl() {
        publishSubject = PublishSubject.create();
        publishSubject.delay(2, TimeUnit.SECONDS)
                .filter(aLong -> !isAvailable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (!NetWorkUtil.isNetworkAvailable()) {
//                        DialogUtil.showNetworkUnAvailable();
                    }
                }, Throwable::printStackTrace);
    }

    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        LogUtils.i("onAvailable" + network.toString());
        isAvailable = true;
    }

    @Override
    public void onUnavailable() {
        super.onUnavailable();
    }

    @Override
    public void onLosing(Network network, int maxMsToLive) {
        super.onLosing(network, maxMsToLive);
        LogUtils.e("onLosing" + network.toString());
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        LogUtils.i("onLost" + network.toString());
        tryShowUnavailable();

    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        LogUtils.i("onCapabilitiesChanged" + network.toString());
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                ToastUtil.showShort("wifi已经连接");
            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                ToastUtil.showShort("数据流量已经连接");
            }
            isAvailable = true;
        } else {
            tryShowUnavailable();
        }
    }

    private void tryShowUnavailable() {
        isAvailable = false;
        publishSubject.onNext(1L);
    }
}
