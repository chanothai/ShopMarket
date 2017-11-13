package com.company.zicure.shopmarket.util;

import android.os.Handler;

/**
 * Created by 4GRYZ52 on 10/21/2016.
 */
public class NextzyUtil {

    public static void launchDelay(final LaunchCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onLaunchCallback();
                }
            }
        },1000);
    }

    public static void requestDelay(final LaunchCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onLaunchCallback();
                }
            }
        },10000);
    }

    public static void launch(final LaunchCallback callback){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onLaunchCallback();
                }
            }
        });
    }

    public interface LaunchCallback {
        void onLaunchCallback();
    }
}
