package com.liueq.quicklibrary;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liueq on 9/10/2017.
 */

public class SilverUtils {

    private static final String TAG = "SilverUtils";

    public static float dpToPixel(int dp, Context context) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        Log.d(TAG, "dpToFloat: " + px);
        return px;
    }

    public static float spToPixel(int sp, Context context) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        Log.d(TAG, "spToFloat: " + px);
        return px;
    }

    public static String generateMd5(String str) {
        String strPasswordMD5 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte buf[] = digest.digest(str.getBytes());
            String stmp = null;
            for (int n = 0; n < buf.length; n++) {
                stmp = Integer.toHexString(buf[n] & 0xff);
                strPasswordMD5 = stmp.length() == 1 ?
                        (strPasswordMD5 + "0" + stmp) : (strPasswordMD5 + stmp);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return strPasswordMD5;
    }

}
