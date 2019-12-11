package com.china.bosh.mylibrary.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限管理
 * checkSelfPermission方法返回等于PackageManager.PERMISSION_GRANTED，表示用户之前同意授权，则继续之前想要进行的操作，
 * 否则判断shouldShowRequestPermissionRationale，返回是否为true，
 * 若为true，则表示用户之前已经禁用该权限，则提示用户该权限的作用，让用户选择是否开放权限
 * 若为false，则申请权限
 */
public class PermissionUtil {

    private static Map<String, PermissionAction> sActionMap = new HashMap<>();

    public static void requestPermission(final Activity activity, String[] permissions, PermissionAction action) {
        requestPermission(activity, true, permissions, action == null ? null : new PermissionAction[]{action});
    }

    public static void requestPermission(final Activity activity, boolean showDialog, String[] permissions) {
        requestPermission(activity, showDialog, permissions, null);
    }

    public static void requestPermission(final Activity activity, boolean showDialog, String[] permissions, PermissionAction[] actions) {
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            PermissionAction action = actions == null ? null : actions[i];
            if (ContextCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                //未拥有该权限，申请权限
                sActionMap.put(permission, action);
                ActivityCompat.requestPermissions(activity, new String[]{permission}, 1);
            } else {
                //已经拥有该权限
                if (action != null) {
                    action.onGranted();
                }
            }
        }
    }

    public static void requestPermission(final Fragment fragment, String[] permissions, PermissionAction action) {
        requestPermission(fragment, true, permissions, action == null ? null : new PermissionAction[]{action});
    }

    public static void requestPermission(final Fragment fragment, boolean showDialog, String[] permissions) {
        requestPermission(fragment, showDialog, permissions, null);
    }

    public static void requestPermission(final Fragment fragment, boolean showDialog, String[] permissions, PermissionAction[] actions) {
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            PermissionAction action = actions == null ? null :  actions[i];
            if (ContextCompat.checkSelfPermission(fragment.getContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                //未拥有该权限，申请权限
                sActionMap.put(permission, action);
                fragment.requestPermissions(new String[]{permission}, 1);
            } else {
                //已经拥有该权限
                if (action != null) {
                    action.onGranted();
                }
            }
        }
    }

    public static void notifyPermissionsChange(final Activity activity, String[] permissions, int[] grantResults) {
        int permissionSize = permissions.length;
        for (int i = 0; i < permissionSize; i++) {
            String permission = permissions[i];
            if (sActionMap.containsKey(permission)) {
                PermissionAction action = sActionMap.get(permission);
                if (action != null) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        //用户同意了授权
                        action.onGranted();
                    } else {
                        //用户拒绝了授权
                        action.onDenied();

                        //用户选择不在提示，向用户展示该权限作用
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                            new AlertDialog.Builder(activity)
                                .setMessage("需要在系统设置里开启权限才能正常使用此功能")
                                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        intent.setData(Uri.parse("package:" + activity.getPackageName()));
                                        activity.startActivity(intent);
                                    }
                                })
                                .setNegativeButton("暂不", null)
                                .create()
                                .show();
                        }
                    }
                }
                sActionMap.remove(permission);
            }
        }
    }
    public static void notifyPermissionsChange(final Fragment fragment, String[] permissions, int[] grantResults) {
        int permissionSize = permissions.length;
        for (int i = 0; i < permissionSize; i++) {
            String permission = permissions[i];
            if (sActionMap.containsKey(permission)) {
                PermissionAction action = sActionMap.get(permission);
                if (action != null) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        //用户同意了授权
                        action.onGranted();
                    } else {
                        //用户拒绝了授权
                        action.onDenied();

                        //用户选择不在提示，向用户展示该权限作用
                        if (!fragment.shouldShowRequestPermissionRationale(permission)) {
                            new AlertDialog.Builder(fragment.getContext())
                                .setMessage("需要在系统设置里开启权限才能使用此功能")
                                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        intent.setData(Uri.parse("package:" + fragment.getContext().getPackageName()));
                                        fragment.startActivity(intent);
                                    }
                                })
                                .setNegativeButton("暂不", null)
                                .create()
                                .show();
                        }
                    }
                }
            }
            sActionMap.remove(permission);
        }
    }

    public interface PermissionAction {

        void onGranted();

        void onDenied();
    }
}