package com.app.framework.basic;

import android.app.Application;

/**
 * Application基类，可以继承此Application
 * Created by JayChen on 16/3/17.
 */
public class BasicApplication extends Application{

    public static BasicApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
    }

    /**
     * 获取此Application的单例,但不允许覆盖
     * @return 当前Application
     */
    public final static BasicApplication getInstance(){
        return mApplication;
    }
}
