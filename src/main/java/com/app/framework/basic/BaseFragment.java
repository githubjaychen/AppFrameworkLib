package com.app.framework.basic;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 此类为Activity的基类
 * 参考文献 《设计模式之禅》 模板方法设计模式
 * Created by JayChen on 16/3/17.
 */
public  abstract class BaseFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=loadFragment();
        return view;
    }

    /**
     * 加载Fragment
     * @return
     */
    private View loadFragment(){
        View view=initView();
        findViews();
        loadViewData();
        bindEvent();
        return view;
    }


    /**
     * 初始化界面View(模板函数)
     * @return
     */
    protected abstract View initView();

    /**
     * find视图控件(模板过程)
     */
    protected void findViews(){}


    /**
     * 加载界面数据(模板过程)
     */
    protected void loadViewData(){};

    /**
     * 绑定界面事件(模板过程)
     */
    protected void bindEvent(){};

}
