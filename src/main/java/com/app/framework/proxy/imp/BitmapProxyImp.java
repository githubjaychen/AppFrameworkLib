package com.app.framework.proxy.imp;

import android.widget.ImageView;

import com.app.framework.basic.BasicApplication;
import com.app.framework.proxy.interfaces.IBitmap;

import net.tsz.afinal.FinalBitmap;


/**
 * 第三方框架图片处理代理类,并采用单例模式
 * 参考文献 《设计模式之禅》 代理模式,单例模式
 * Created by JayChen on 16/3/17.
 */
public class BitmapProxyImp implements IBitmap {

    private FinalBitmap mFinalBitmap;//final框架图片处理类
    private static BitmapProxyImp mBitmapProxyImp;//当前类实例

    private BitmapProxyImp(){
        BasicApplication context=BasicApplication.getInstance();
        mFinalBitmap=FinalBitmap.create(context);
    }

    /**
     * 获取当前实例
     * @return 第三方框架图片处理代理
     */
    public static final BitmapProxyImp  getInstance(){
        if(mBitmapProxyImp==null){
            mBitmapProxyImp=new BitmapProxyImp();
        }
        return mBitmapProxyImp;
    }


    @Override
    public void loadImage(ImageView loadImageView, String url) {
        mFinalBitmap.display(loadImageView,url);
    }
}
