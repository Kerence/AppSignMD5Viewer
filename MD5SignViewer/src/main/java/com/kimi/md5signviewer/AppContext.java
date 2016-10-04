package com.kimi.md5signviewer;

import android.app.Application;

import com.clcw.mobile.util.ToastUtils;

/**
 * Created by kimi on 2016/5/2.
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
    }
}
