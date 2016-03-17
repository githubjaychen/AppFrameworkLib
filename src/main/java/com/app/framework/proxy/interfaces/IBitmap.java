package com.app.framework.proxy.interfaces;

import android.widget.ImageView;

/**
 * 抽象代理类,此类专门代理第三方框架图片加载的实现,采用代理模式的目的是很好的解决了第三方框架变更导致的时间成本
 * 注:本接口被代理者本应当继承，但考虑第三方框架修改成本太大，所以只能代理具体类
 * 参考文献 《设计模式之禅》 代理模式
 * Created by JayChen on 16/3/17.
 */
public interface IBitmap {

    /**
     * 加载图片
     * @param loadImageView 待加载的图片控件
     * @param url 图片请求地址
     */
    void loadImage(ImageView loadImageView, String url);
}
