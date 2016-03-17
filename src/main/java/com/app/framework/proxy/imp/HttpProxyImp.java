package com.app.framework.proxy.imp;


import com.app.framework.basic.BasicException;
import com.app.framework.proxy.interfaces.IHttp;
import com.app.framework.proxy.interfaces.IRequstCallback;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 第三方框架网络处理代理类
 * 参考文献 《设计模式之禅》 代理模式
 * Created by JayChen on 16/3/17.
 */
public class HttpProxyImp implements IHttp {

    private FinalHttp mFinalHttp;//final框架网络请求处理类

    public HttpProxyImp(){
        mFinalHttp=new FinalHttp();
        String  str;
    }

    @Override
    public void post(String requestUrl, IRequstCallback requstCallback) {
        AjaxCallBack ajaxCallBack=transformCallBack(requstCallback);
        mFinalHttp.post(requestUrl,ajaxCallBack);
    }

    @Override
    public void post(String requestUrl, HashMap<String, Object> requestParams, IRequstCallback requstCallback) {
        AjaxCallBack ajaxCallBack=transformCallBack(requstCallback);
        try{
            AjaxParams ajaxParams=transformRequestParams(requestParams);
            mFinalHttp.post(requestUrl,ajaxParams,ajaxCallBack);
        }catch (RequestIllegalArgument requestIllegalArgument){
            requstCallback.onError();//处理异常
        }
    }

    @Override
    public void get(String requestUrl, IRequstCallback requstCallback) {
        AjaxCallBack ajaxCallBack=transformCallBack(requstCallback);
        mFinalHttp.get(requestUrl, ajaxCallBack);
    }

    @Override
    public void get(String requestUrl, HashMap<String, Object> requestParams, IRequstCallback requstCallback) {
        AjaxCallBack ajaxCallBack=transformCallBack(requstCallback);
        try{
            AjaxParams ajaxParams=transformRequestParams(requestParams);
            mFinalHttp.get(requestUrl,ajaxParams,ajaxCallBack);
        }catch (RequestIllegalArgument requestIllegalArgument){
            requstCallback.onError();//处理异常
        }

    }

    /**
     * 转换IRequstCallback->AjaxCallBack,转换成Afinal框架需要的类型
     * @param requstCallback 本软件的网络请求回调类
     * @return Afinal框架的数据返回类
     */
    private AjaxCallBack transformCallBack(final IRequstCallback requstCallback){
        AjaxCallBack<String> ajaxCallBack=new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                requstCallback.onSuccess(json);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                requstCallback.onError();
            }
        };

        return ajaxCallBack;
    }

    /**
     * 转换HashMap->AjaxParams,转换成Afinal框架需要的类型
     * @param requestParams 请求参数
     * @return Afinal框架请求参数类
     */
    private AjaxParams transformRequestParams(HashMap<String,Object> requestParams) throws RequestIllegalArgument{
        AjaxParams ajaxParams=new AjaxParams();
        Iterator iterator=requestParams.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String,Object> entry=(Map.Entry<String,Object>)iterator.next();
            String requestKey=entry.getKey();
            Object requestValue=entry.getValue();
            if(requestValue instanceof String){
                ajaxParams.put(requestKey,requestValue+"");
            }else if (requestValue instanceof File) {
                try {
                    File fileValue = (File) requestValue;
                    ajaxParams.put(requestKey, fileValue);
                }catch (FileNotFoundException exception){
                    //根据《代码大全》的描述 200页，不应当把低层的异常抛給调用者，破坏了封装性。 那么解决思路是自定义一个 请求参数非法的异常 抛给调用者
                    RequestIllegalArgument requestIllegalArgument=new RequestIllegalArgument("未找到文件");
                    throw requestIllegalArgument;
                }

            }
        }
        return ajaxParams;
    }


    /**
     * 请求参数异常
     */
    private class RequestIllegalArgument extends BasicException {
        public RequestIllegalArgument(String info){
            super(info);
        }
    }

}
