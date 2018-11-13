package com.guocai.gclive;

import android.app.Application;
import android.content.Context;

/**
 * Create  By xrj ON 2018/9/12 0012
 */
public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化context对象，context对象使用的非常多
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
