package com.liueq.quicklibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

/**
 * Created by liueq on 1/11/2017.
 * 各种跳转的 Intent
 */

public class AnyWhereIntent {

    private static final String PKG_SCHEME = "package:";

    /**
     * 跳转到应用详情界面
     *
     * @param pkgName
     * @return
     */
    public static Intent appDetailSettings(String pkgName) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse(PKG_SCHEME + pkgName));
        return intent;
    }

    /**
     * 修改系统设置
     * <p>
     * <uses-permission android:name="android.permission.WRITE_SETTINGS"/>
     *
     * @param pkgName
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static Intent writeSettings(String pkgName) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse(PKG_SCHEME + pkgName));
        return intent;
    }

    /**
     * 能否在其他应用上层显示
     * <p>
     * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
     *
     * @param pkgName
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static Intent drawOverlay(String pkgName) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse(PKG_SCHEME + pkgName));
        return intent;
    }
}
