package com.app.framework.basic;


import com.app.framework.proxy.interfaces.IRequstCallback;

/**
 * 网络请求回调基类
 * Created by JayChen on 16/3/17.
 */
public class RequestCallBack implements IRequstCallback {

    @Override
    public void onSuccess() {}

    @Override
    public void onSuccess(String json) {}

    @Override
    public void onError() {}

    @Override
    public void onError(Throwable t, int errorNo, String strMsg) {}
}
