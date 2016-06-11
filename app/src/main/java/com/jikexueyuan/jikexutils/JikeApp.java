package com.jikexueyuan.jikexutils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by xuxihong on 16/5/6.
 */
public class JikeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
