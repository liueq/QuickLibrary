package com.liueq.quicklibrary;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

/**
 * Created by liueq on 1/11/2017.
 * 所有的权限组，权限
 * 除了通常的权限之外，Android 还提供了一些特殊，危险的权限。
 */

public class Permissions {

    public static final String[] CALENDAR;
    public static final String[] CAMERA;
    public static final String[] CONTACTS;
    public static final String[] LOCATION;
    public static final String[] MICROPHONE;
    public static final String[] PHONE;
    public static final String[] SENSORS;
    public static final String[] SMS;
    public static final String[] STORAGE;

    static {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            CALENDAR = new String[]{};
            CAMERA = new String[]{};
            CONTACTS = new String[]{};
            LOCATION = new String[]{};
            MICROPHONE = new String[]{};
            PHONE = new String[]{};
            SENSORS = new String[]{};
            SMS = new String[]{};
            STORAGE = new String[]{};
        } else {
            CALENDAR = new String[]{
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR
            };
            CAMERA = new String[]{
                    Manifest.permission.CAMERA
            };
            CONTACTS = new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS
            };
            LOCATION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            MICROPHONE = new String[]{
                    Manifest.permission.RECORD_AUDIO
            };
            PHONE = new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.WRITE_CALL_LOG,
                    Manifest.permission.ADD_VOICEMAIL,
                    Manifest.permission.USE_SIP,
                    Manifest.permission.PROCESS_OUTGOING_CALLS
            };
            SENSORS = new String[]{
                    Manifest.permission.BODY_SENSORS
            };
            SMS = new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_WAP_PUSH,
                    Manifest.permission.RECEIVE_MMS
            };
            STORAGE = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
        }
    }

    /**
     * 特殊权限，能否修改系统设置
     * <uses-permission android:name="android.permission.WRITE_SETTINGS"/>
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean canWriteSettings(Context context){
        return Settings.System.canWrite(context);
    }

    /**
     * 能否在应用上层绘制
     * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean canDrawOverlay(Context context){
        return Settings.canDrawOverlays(context);
    }

    public interface ReqPermissionCallback {
        void requestPermissionResult(boolean grant);

        void requestMultiPermissionResult(String[] grantedPermissions, String[] deniedPermissions);
    }
}
