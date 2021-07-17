//================================================================================================================================
//
// Copyright (c) 2015-2021 VisionStar Information Technology (Shanghai) Co., Ltd. All Rights Reserved.
// EasyAR is the registered trademark or trademark of VisionStar Information Technology (Shanghai) Co., Ltd in China
// and other countries for the augmented reality technology developed by VisionStar Information Technology (Shanghai) Co., Ltd.
//
//================================================================================================================================

package cn.easyar.samples.helloar;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import cn.easyar.CameraDevice;
import cn.easyar.Engine;
import cn.easyar.ImageTracker;

public class ARActivity extends Activity
{
    /*
    * Steps to create the key for this sample:
    *  1. login www.easyar.com
    *  2. create app with
    *      Name: HelloAR
    *      Package Name: cn.easyar.samples.helloar
    *  3. find the created item in the list and show key
    *  4. set key string bellow
    */
    private static String key = "drEsZ3KiNHtqxMC23fmpa3sk/OK9lR4S5yMEHEaDGkxykxxRRp4LHAnSTg0DwkcICsdPCHOBDhBQnxIcH9ISX0CEGkx4lQZ3V9JFDx/SE1dQlRFNVoNdBGiLXVxGnhtSVrkbTRHKJGMf0glfQZkeUEeDXQRo0hxRXp0KUFqEBhxu3F1OX5ELWFyCEk0RyiQcRJkRWlyHDBwf0hJfUNIiEhGdEFpGnBpNEcokHECVEU1W3jZTUpcaakGRHFVanhgcH9IMW12DGhBwnBBLV6IaXVyXEVdHmRBQEdxdTVaeDFsdohpdXIIbV12XXRIRgxpQQJVRcVGaGl1HpA1fUJsWUFTSUxxAlRFNVt4sS0GWHl1WpA1fUJsWUFTSUxxAlRFNVt4sTlKCDFtggB5KWpETc1KAXRIRgxpQQJVRc1yEFlFdpA1fUJsWUFTSUxxAlRFNVt47W12DGm1DkQtXUpwyX0PSUxxAlRFNVt48f3ekDV9QmxZQVNIiEhGVB05aghpqWp0abUeREk4RyhFLX5xTHFqDM1FQkRMcCZYeUkCVAhJI0h1LXZQTW3qUDBwJq11dXd4aX0CJHkwdgx5TQ5waTR2YGlJfnx5MEa1THEWRDVdSngtNEcokHFCfElNGnhZKStIiEhGAE19HlhBMXoNdBGjSHlBXghBXV9IiEhGdEFpGnBpNEcokHECVEU1W3jZTUpcaakGRHFVanhgcH9IMW12DGhBwnBBLV6IaXVyXEVdHmRBQEdxdTVaeDFsdohpdXIIbV12XXRIRgxpQQJVRcVGaGl1HpA1fUJsWUFTSUxxAlRFNVt4sS0GWHl1WpA1fUJsWUFTSUxxAlRFNVt4sTlKCDFtggB5KWpETc1KAXRIRgxpQQJVRc1yEFlFdpA1fUJsWUFTSUxxAlRFNVt47W12DGm1DkQtXUpwyX0PSUxxAlRFNVt48f3ekDV9QmxZQVNIiEhGVB05aghpqWp0abUeREk4RyhFLX5xTHFqDM1FQkRMcCZYeUkCVAhJI0h1LXZQTW3qUDBwJq10cbtxdSFKCFl9dhAwcCatdXVydEktdmQtHEa1THEOcHkpVnw1TQNJFZRGZEE0RrVMcXp8bS1+VDBwJq11NVp4MWx25El9UlStMUpMUV12XXRIRgxpQQJVRfV+fClphlRxRVJ4WSlqfERwf0gxbXYMaEGGVHFFBlBZQVNJTHECVEU1W3jBcWZUcSmeCHl1YmRFZEdxdTVaeDFsdowpMVZEcW2eCHl1YmRFZEdxdTVaeDFsdow9fQYMabUORC1dSnDJfQ9JTHECVEU1W3jJRR5kQUGeCHl1YmRFZEdxdTVaeDFsdtBpQQJUsTlKEFl9fvR5OEdxdTVaeDFsdsz56Z4IeXViZEVkRrVMcVogPV0GVK1delSxKUp0PHAmeClJf3F1XQLwQXVKcXQRVkRNNVo0iQ3+Yq6qqaUsGTZRwzf2rbooZAKR7w0L1s28TWktzp052MIigy6tqEzjJizozDGltL4MY/DR+Pq5doQU0FtlzGv8Pae+r2MMtlhqKVvLvgUwZHtv7erP+akOdy/kpYW4tssHsprMhdHlOJN6JIw7G+Rr04crX6Onrer9I7szYRwKmdAMQIy6kijfZYyIXztitfdqLhKnVyoNk0l/lqlEfvTMedcTo2sgHrOd6WRsM49ormwj7htYE5K/9zih62X2YpI9HisS8qzEhG0nJFZc3v6yVwBS46HqOePfTivsf3DmOtynEkVXxkqyJv06T3yyCCKIoD4n6pDx8LH1lXjPwfz4=";
    private GLView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (!Engine.initialize(this, key)) {
            Log.e("HelloAR", "Initialization Failed.");
            Toast.makeText(ARActivity.this, Engine.errorMessage(), Toast.LENGTH_LONG).show();
            return;
        }
        if (!CameraDevice.isAvailable()) {
            Toast.makeText(ARActivity.this, "CameraDevice not available.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!ImageTracker.isAvailable()) {
            Toast.makeText(ARActivity.this, "ImageTracker not available.", Toast.LENGTH_LONG).show();
            return;
        }

        glView = new GLView(this);

        requestCameraPermission(new PermissionCallback() {
            @Override
            public void onSuccess() {
                ((ViewGroup) findViewById(R.id.preview)).addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }

            @Override
            public void onFailure() {
            }
        });
    }

    private interface PermissionCallback
    {
        void onSuccess();
        void onFailure();
    }
    private HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<Integer, PermissionCallback>();
    private int permissionRequestCodeSerial = 0;
    @TargetApi(23)
    private void requestCameraPermission(PermissionCallback callback)
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                int requestCode = permissionRequestCodeSerial;
                permissionRequestCodeSerial += 1;
                permissionCallbacks.put(requestCode, callback);
                requestPermissions(new String[]{Manifest.permission.CAMERA}, requestCode);
            } else {
                callback.onSuccess();
            }
        } else {
            callback.onSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (permissionCallbacks.containsKey(requestCode)) {
            PermissionCallback callback = permissionCallbacks.get(requestCode);
            permissionCallbacks.remove(requestCode);
            boolean executed = false;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    executed = true;
                    callback.onFailure();
                }
            }
            if (!executed) {
                callback.onSuccess();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (glView != null) { glView.onResume(); }
    }

    @Override
    protected void onPause()
    {
        if (glView != null) { glView.onPause(); }
        super.onPause();
    }
}
