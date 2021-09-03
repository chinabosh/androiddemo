package com.china.bosh.mylibrary.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.china.bosh.mylibrary.provider.ContextProvider;

/**
 * NetWork Utils
 * <ul>
 * <strong>Attentions</strong>
 * <li>You should add <strong>android.permission.ACCESS_NETWORK_STATE</strong> in manifest, to get network status.</li>
 * </ul>
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-11-03
 */
public class NetWorkUtil {

    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final String NETWORK_TYPE_3G = "eg";
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_WAP = "wap";
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";
    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";

    /**
     * Get network type
     *
     * @param -context
     * @return
     */
    public static String getNetWorkType() {
        ConnectivityManager manager = (ConnectivityManager) ContextProvider.get().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        String type = NETWORK_TYPE_DISCONNECT;
        if (manager == null || (networkInfo = manager.getActiveNetworkInfo()) == null) {
            return type;
        }

        if (networkInfo.isConnected()) {
            String typeName = networkInfo.getTypeName();
            if ("WIFI".equalsIgnoreCase(typeName)) {
                type = NETWORK_TYPE_WIFI;
            } else if ("MOBILE".equalsIgnoreCase(typeName)) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                type = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork() ? NETWORK_TYPE_3G : NETWORK_TYPE_2G)
                        : NETWORK_TYPE_WAP;
            } else {
                type = NETWORK_TYPE_UNKNOWN;
            }
        }
        return type;
    }

    /**
     * Whether is fast mobile network
     *
     * @param -context
     * @return
     */
    private static boolean isFastMobileNetwork() {
        TelephonyManager telephonyManager = (TelephonyManager) ContextProvider.get().getContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return false;
        }

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_IDEN:
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return false;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true;
        }
    }


    /**
     * 网络是否可用
     *
     * @param -context
     * @return
     */
    public static boolean isNetworkAvailable() {
        if (Build.VERSION.SDK_INT < 23) {
            ConnectivityManager mgr = (ConnectivityManager) ContextProvider.get().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] info = mgr.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } else {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) ContextProvider.get().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (int i=0; i < networks.length; i++){
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void checkNetworkConfig() {
        Context context = ContextProvider.get().getContext();
        ConnectivityManager conMan = (ConnectivityManager) ContextProvider.get().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile 3G Data Network
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        int sdkVersion = Build.VERSION.SDK_INT;
        if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) || (wifi == NetworkInfo.State.DISCONNECTED)) {
            //进入wifi网络设置界面
            if (sdkVersion > 10) {
                intent.setAction(Settings.ACTION_WIFI_SETTINGS);
            } else {
                ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.WIFI_SETTINGS");
                intent.setComponent(comp);
                intent.setAction("android.intent.action.VIEW");
            }
        } else {
            //进入无线网络配置界面
            if (sdkVersion > 10) {
                intent.setAction(Settings.ACTION_WIRELESS_SETTINGS);
            } else {
                ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                intent.setComponent(comp);
                intent.setAction("android.intent.action.VIEW");
            }
        }
        context.startActivity(intent);
    }
}
