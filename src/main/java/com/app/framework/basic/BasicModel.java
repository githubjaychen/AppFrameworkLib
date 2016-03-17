package com.app.framework.basic;


import com.app.framework.proxy.imp.HttpProxyImp;

/**
 * 模型层基类
 * Created by Jaychen on 16/3/17.
 */
public class BasicModel{

    private HttpProxyImp mHttpProxyImp;

    public BasicModel(){
        mHttpProxyImp=new HttpProxyImp();
    }

    /**
     * 获取网络请求框架代理类,统一的获取方式，为第三方网络处理代理实现类变更减少成本
     * @return
     */
    public HttpProxyImp getHttpProxyImp() {
        return mHttpProxyImp;
    }
}
