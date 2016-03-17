package com.app.framework.proxy.interfaces;

import java.util.HashMap;

/**
 * 抽象代理类,此类专门代理第三方框架网络请求的实现,采用代理模式的目的是很好的解决了第三方框架变更导致的时间成本
 * 注:本接口被代理者本应当继承，但考虑第三方框架修改成本太大，所以只能代理具体类
 * 参考文献 《设计模式之禅》 代理模式
 * Created by JayChen on 16/3/17.
 */
public interface IHttp {

    /**
     * post请求
     * @param requestUrl 请求url
     * @param requstCallback 请求回调
     */
    void post(String requestUrl, IRequstCallback requstCallback);

    /**
     * post请求
     * @param requestUrl 请求url
     * @param requestParams 请求参数
     * @param requstCallback 请求回调
     */
    void post(String requestUrl, HashMap<String, Object> requestParams, IRequstCallback requstCallback);

    /**
     * get请求
     * @param requestUrl 请求url
     * @param requstCallback 请求回调
     */
    void get(String requestUrl, IRequstCallback requstCallback);

    /**
     * get请求
     * @param requestUrl 请求url
     * @param requestParams 请求参数
     * @param requstCallback 请求回调
     */
    void get(String requestUrl, HashMap<String, Object> requestParams, IRequstCallback requstCallback);
}
