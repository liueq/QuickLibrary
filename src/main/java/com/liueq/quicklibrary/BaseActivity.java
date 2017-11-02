package com.liueq.quicklibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liueq on 31/10/2017.
 * 最基础的 Activity
 */

public class BaseActivity extends AppCompatActivity implements Permissions.ReqPermissionCallback {

    public static final int REQ_CODE_PERMISSION = 8000;
    public static final int REQ_CODE_PERMISSIONS = 8001;

    Permissions.ReqPermissionCallback mReqPermissionCallback = this;

    protected final void requestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            mReqPermissionCallback.requestPermissionResult(true);
            return;
        }

        ActivityCompat.requestPermissions(this, new String[]{permission}, REQ_CODE_PERMISSION);
    }

    protected final void requestPermission(String permission, Permissions.ReqPermissionCallback callback) {
        mReqPermissionCallback = callback;
        requestPermission(permission);
    }

    protected final void requestPermissions(String... permissions) {
        boolean allGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                allGranted = false;
            }
        }

        if (allGranted) {
            mReqPermissionCallback.requestMultiPermissionResult(permissions, null);
        } else {
            ActivityCompat.requestPermissions(this, permissions, REQ_CODE_PERMISSIONS);
        }
    }

    protected final void requestPermissions(Permissions.ReqPermissionCallback callback, String... permissions) {
        mReqPermissionCallback = callback;
        requestPermissions(permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE_PERMISSION) {
            if (mReqPermissionCallback == null) {
                mReqPermissionCallback = this;
            }

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mReqPermissionCallback.requestPermissionResult(true);
            }else{
                if(!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])){
                    createPermissionDeniedDialog().show();
                }
                mReqPermissionCallback.requestPermissionResult(false);
            }
        } else if (requestCode == REQ_CODE_PERMISSIONS) {
            if (mReqPermissionCallback == null) {
                mReqPermissionCallback = this;
            }
            List<String> grantedPermissions = new ArrayList<>();
            List<String> deniedPermissions = new ArrayList<>();
            boolean neverRequest = false;
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    grantedPermissions.add(permissions[i]);
                } else {
                    if(!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])){
                        neverRequest = true;
                    }
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (neverRequest) {
                createPermissionDeniedDialog(deniedPermissions.toArray(new String[]{})).show();
            }

            mReqPermissionCallback.requestMultiPermissionResult(grantedPermissions.toArray(new String[]{}), deniedPermissions.toArray(new String[]{}));
        }
    }

    @Override
    public void requestPermissionResult(boolean grant) {

    }

    @Override
    public void requestMultiPermissionResult(String[] grantedPermissions, String[] deniedPermissions) {

    }

    protected AlertDialog createPermissionDeniedDialog(String ...deniedPermissions){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title_alert)
                .setMessage(R.string.dialog_title_permission_denied)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_go_settings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = AnyWhereIntent.appDetailSettings(getPackageName());
                        startActivity(intent);
                    }
                });

        return builder.create();
    }
}
