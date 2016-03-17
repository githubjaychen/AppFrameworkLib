package com.app.framework.proxy.interfaces;

/**
 * 此类是请求接口回调，负责对网络请求的回调功能
 * Created by JayChen on 3/16/16.
 */
public interface IRequstCallback {

    /**
     * 数据返回成功
     */
    void onSuccess();

    /**
     * 数据成功
     * @param json json数据
     */
    void onSuccess(String json);

    /**
     * 失败
     */
    void onError();


    /**
     * 异常
     * @param t 异常
     * @param errorNo 错误码
     * @param strMsg 错误消息
     */
    void onError(Throwable t, int errorNo, String strMsg);
}
