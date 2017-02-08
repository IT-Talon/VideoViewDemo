package com.talon.videoviewdemo;

import android.app.Application;

import com.danikula.videocache.HttpProxyCacheServer;

/**
 * Created by Talon on 2017/2/8.
 */

public class MyApplication extends Application {
    private HttpProxyCacheServer proxy;

    private static MyApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static MyApplication getInstance() {
        return sApplication;
    }

    public HttpProxyCacheServer getProxy() {
        if (this.proxy == null) {
            this.proxy = new HttpProxyCacheServer(this);
        }
        return this.proxy;
    }
}
