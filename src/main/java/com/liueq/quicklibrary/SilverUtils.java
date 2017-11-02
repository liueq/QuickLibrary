package com.liueq.quicklibrary;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by liueq on 9/10/2017.
 */

public class SilverUtils {

    private static final String TAG = "SilverUtils";

    public static float dpToPixel(int dp, Context context){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        Log.d(TAG, "dpToFloat: " + px);
        return px;
    }

    public static float spToPixel(int sp, Context context){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        Log.d(TAG, "spToFloat: " + px);
        return px;
    }
}
